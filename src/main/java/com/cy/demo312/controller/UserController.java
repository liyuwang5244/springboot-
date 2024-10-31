package com.cy.demo312.controller;

import com.cy.demo312.controller.ex.*;
import com.cy.demo312.entity.User;
import com.cy.demo312.service.IUserService;
import com.cy.demo312.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.cy.demo312.controller.BaseController.OK;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private BaseController baseController;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);

        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword (String oldPassword,
                                            String newPassword,
                                            HttpSession session) {
        Integer uid = baseController.getUidFromSession(session);
        String username = baseController.getUsernameFromSession(session);
        userService.changePassword(username, uid, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid (HttpSession session) {
        Integer uid = baseController.getUidFromSession(session);

        User user = userService.findByUid(uid);

        return new JsonResult<>(OK, user);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> updateInfo (User user,
                                        HttpSession session) {
        Integer uid = baseController.getUidFromSession(session);
        String username = baseController.getUsernameFromSession(session);
        userService.updateInfo(username, uid, user);
        return new JsonResult<>(OK);
    }

    /*设置上传文件的最大值*/
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    /*限制上传文件的类型*/
    public static final List<String> AVATAR_TYPES = new ArrayList<>();
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    /**
     * 修改头像
     * @param session
     * @param file 任何类型的文件
     * @return json
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar (
            HttpSession session,
            @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileEmptyException("上传头像文件不允许为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("上传头像文件不能超过10M");
        }
        if (!AVATAR_TYPES.contains(file.getContentType())) {
            throw new FileTypeException("上传头像只允许使用JPG、PNG、BMP、GIF中的一种");
        }
        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        //检测目录是否存在
        if (!dir.exists()) {
            //创建当前目录
            dir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename = " + originalFilename);
        //获取后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString().toUpperCase();
        filename = filename + suffix;
        File dest = new File(dir, filename);

        //将file数据写入dest
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }

        Integer uid = baseController.getUidFromSession(session);
        String username = baseController.getUsernameFromSession(session);
        String avatar = "/upload/" + filename;
        userService.changeAvatar(uid, avatar, username);
        //返回用户头像路径将来用于展示使用
        return new JsonResult<>(OK, avatar);
    }
}
