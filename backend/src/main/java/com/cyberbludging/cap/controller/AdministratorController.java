package com.cyberbludging.cap.controller;

import com.cyberbludging.cap.entity.User;
import com.cyberbludging.cap.service.*;
import com.cyberbludging.cap.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "管理员接口")
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/users")
    @ApiOperation("获取用户列表")
    public List<User> getUsers() {
        return administratorService.getAllUsers();
    }

    @DeleteMapping("/users/{userId}")
    @ApiOperation("删除用户")
    public Result deleteUser(@PathVariable Integer userId) {
        return administratorService.deleteUser(userId);
    }
}