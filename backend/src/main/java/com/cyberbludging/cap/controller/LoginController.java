package com.cyberbludging.cap.controller;


import com.cyberbludging.cap.service.*;
import com.cyberbludging.cap.util.Result;
import com.cyberbludging.cap.util.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@Api(tags = "登录接口")
@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    //private LoginService loginService;

    @PostMapping("")
    @ApiOperation("用户登录")
    public Result login(@RequestBody UserDTO user) {
        return loginService.login(user.getId(), user.getPassword(), user.getIsUser());
    }

    @GetMapping("/check")
    @ApiOperation("登录验证")
    public Claims checkToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return JwtUtil.getClaimsByToken(token);
    }
}

