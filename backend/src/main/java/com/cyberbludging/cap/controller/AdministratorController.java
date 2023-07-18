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

    /*
    @GetMapping("/users/{userId}")
    @ApiOperation("获取用户信息")
    public UserDTO getUser(@PathVariable Integer userId) {

    }
    */

    /*
    @PutMapping("/users/{userId}")
    @ApiOperation("修改用户信息")
    public Result updateUser(@PathVariable Integer userId, @RequestBody UserDTO user) {

    }
    */


    @DeleteMapping("/users/{userId}")
    @ApiOperation("删除用户")
    public Result deleteUser(@PathVariable Integer userId) {
        return administratorService.deleteUser(userId);
    }

    /*
    @GetMapping("/feedback")
    @ApiOperation("获取用户反馈列表")
    public Result getFeedbackList() {

    }

    @PostMapping("/feedback/{feedbackId}/process")
    @ApiOperation("处理用户反馈")
    public Result processFeedback(@PathVariable Integer feedbackId, @RequestBody .... feedback) {

    }

    @PostMapping("/school-ad")
    @ApiOperation("上架院校广告位")
    public Result createSchoolAd(@RequestBody .... schoolAd) {

    }

    @PutMapping("/school-ad/{adId}")
    @ApiOperation("下架院校广告位")
    public Result deactivateSchoolAd(@PathVariable Integer adId) {

    }

    @GetMapping("/database")
    @ApiOperation("管理数据库信息")
    public Result manageDatabase() {

    }
    */
}