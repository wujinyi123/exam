package com.system.exam.controller.user;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.common.ImgUploadDTO;
import com.system.exam.domain.dto.user.*;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.domain.qo.user.UdateQO;
import com.system.exam.domain.qo.user.UserMsgQO;
import com.system.exam.domain.qo.user.UserQO;
import com.system.exam.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * 用户
 */
@Validated
@RestController
@RequestMapping("/back/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginQO
     * @return
     */
    @PostMapping("/login")
    public ResponseData<LoginDTO> login(@RequestBody @Valid LoginQO loginQO) {
        return ResponseDataUtil.buildSuccess(userService.login(loginQO));
    }

    /**
     * 更新登录
     * @param userType
     * @return
     */
    @PostMapping("/reLogin")
    public ResponseData<LoginDTO> reLogin(@RequestParam String userType) {
        return ResponseDataUtil.buildSuccess(userService.relogin(userType));
    }

    /**
     * 获取用户信息
     * @param userMsgQO
     * @return
     */
    @PostMapping("/getUserMsg")
    public ResponseData<UserDTO> getUserMsg(@RequestBody @Valid UserMsgQO userMsgQO) {
        return ResponseDataUtil.buildSuccess(userService.getUserMsg(userMsgQO));
    }

    /**
     * 获取用户个人资料
     * @param userQO
     * @return
     */
    @PostMapping("/getUserInfo")
    public ResponseData<UserInfoDTO> getUserInfo(@RequestBody @Valid UserQO userQO) {
        return ResponseDataUtil.buildSuccess(userService.getUserInfo(userQO));
    }

    /**
     * 修改密码
     * @param udateQO
     * @return
     */
    @PostMapping("udatePassword")
    public ResponseData<UdateDTO> udatePassword(@RequestBody @Valid UdateQO udateQO) {
        return ResponseDataUtil.buildSuccess(userService.udatePassword(udateQO));
    }

    /**
     * 修改电话和邮箱
     * @param udateQO
     * @return
     */
    @PostMapping("udateTelEmail")
    public ResponseData<UdateDTO> udateTelEmail(@RequestBody @Valid UdateQO udateQO) {
        return ResponseDataUtil.buildSuccess(userService.udateTelEmail(udateQO));
    }

    /**
     * 上传头像
     * @param file
     * @param type
     * @return
     */
    @PostMapping("/imgUpload")
    public ResponseData<ImgUploadDTO> imgUpload(@RequestParam(value = "file") MultipartFile file, @RequestParam String type) {
        return ResponseDataUtil.buildSuccess(userService.imgUpload(file,type));
    }

}
