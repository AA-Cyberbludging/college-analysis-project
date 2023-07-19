package com.cyberbludging.cap.controller;

import com.cyberbludging.cap.entity.dto.RegisterDTO;
import com.cyberbludging.cap.entity.dto.UserAuthDTO;
import com.cyberbludging.cap.service.AuthenticationService;
import com.cyberbludging.cap.util.JwtUtil;
import com.cyberbludging.cap.util.Result;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationService loginService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody UserAuthDTO user) {
        return loginService.login(user.getName(), user.getPassword(), user.getIsStudent());
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody RegisterDTO user) {
        return loginService.register(user.getName(), user.getPassword(), user.getPname(), user.getSubject(), user.getUserScore(), user.getUserRank());
    }

    @GetMapping("/check")
    @ApiOperation("用户登录验证")
    public Claims checkToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return JwtUtil.getClaimsByToken(token);
    }
}
