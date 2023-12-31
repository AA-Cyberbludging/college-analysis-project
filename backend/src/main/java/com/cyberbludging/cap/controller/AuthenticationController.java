package com.cyberbludging.cap.controller;

import com.cyberbludging.cap.entity.dto.RegisterDTO;
import com.cyberbludging.cap.entity.dto.UserAuthDTO;
import com.cyberbludging.cap.service.AuthenticationService;
import com.cyberbludging.cap.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody UserAuthDTO user) {
        return authService.login(user.getName(), user.getPassword(), user.getIsStudent());
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody RegisterDTO user) {
        return authService.register(user.getName(), user.getPassword(), user.getPname(), user.getSubject(), user.getUserScore(), user.getUserRank());
    }
}
