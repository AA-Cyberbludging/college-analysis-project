package com.cyberbludging.cap.controller;


import com.cyberbludging.cap.entity.User;
import com.cyberbludging.cap.entity.dto.PasswordDTO;
import com.cyberbludging.cap.service.AnalysisInfoService;
import com.cyberbludging.cap.service.UserService;
import com.cyberbludging.cap.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ApiOperation("获取用户信息")
    User getUserInfo(@PathVariable Integer id) {
        return userService.getUserInfo(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("更新用户信息")
    Result updateUserInfo(@PathVariable Integer id,
                          @RequestBody User newInfo) {
        return userService.updateUserInfo(id, newInfo.getUserName(), newInfo.getUserScore(), newInfo.getUserRank(), newInfo.getPname(), newInfo.getSubject());
    }

    @PatchMapping("/{id}/password")
    @ApiOperation("更新用户密码")
    Result updateUserPassword(@PathVariable Integer id, @RequestBody PasswordDTO passwordForm) {
        return userService.updateUserPassword(id, passwordForm.getOldPassword(), passwordForm.getNewPassword());
    }
}
