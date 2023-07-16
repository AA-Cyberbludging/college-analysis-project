package com.cyberbludging.cap.controller;


import com.cyberbludging.cap.entity.User;
import com.cyberbludging.cap.entity.dto.PasswordDTO;
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

    @PostMapping("/{id}")
    @ApiOperation("更新用户信息")
    Result updateUserInfo(@PathVariable Integer id,
                          @RequestBody User newInfo) {
        return userService.updateUserInfo(id, newInfo.getUserName(), newInfo.getUserScore(), newInfo.getPname(), newInfo.getSubject());
    }



    @PostMapping("/{id}/password")
    @ApiOperation("更新用户密码")
    Result updateUserPassword(@PathVariable Integer id, @RequestBody PasswordDTO passwordForm) {
        return userService.updateUserPassword(id, passwordForm.getOldPassword(), passwordForm.getNewPassword());
    }
/*
    @PostMapping("/{userId}/consultation")
    @ApiOperation("一对一咨询")
    public Result requestConsultation(@PathVariable Integer userId, @RequestBody ... request) {

    }


    @PostMapping("/{userId}/score")
    @ApiOperation("填写分数")
    public Result submitScore(@PathVariable Integer userId, @RequestBody ... score) {

    }


    @GetMapping("/{userId}/recommendation")
    @ApiOperation("推荐志愿")
    public RecommendationDTO getRecommendation(@PathVariable Integer userId) {

    }
    */


    //以上是老师发的图片里要求的
    /*
    @PostMapping("/{userId}/score")
    @ApiOperation("展示录取趋势")



    @PostMapping("/{userId}/score")
    @ApiOperation("展示招生分布")


    @PostMapping("/{userId}/score")
    @ApiOperation("展示大学排序")


    @PostMapping("/{userId}/score")
    @ApiOperation("展示热度排名")


    @PostMapping("/{userId}/score")
    @ApiOperation("展示省份数量排名")



    @PostMapping("/{userId}/score")
    @ApiOperation("展示男女比例分析")



    @PostMapping("/{userId}/score")
    @ApiOperation("展示就业率分析")



    @PostMapping("/{userId}/score")
    @ApiOperation("展示升学率分析")



    @PostMapping("/{userId}/score")
    @ApiOperation("展示出国率分析")



    @PostMapping("/{userId}/score")
    @ApiOperation("按位置推荐")



    @PostMapping("/{userId}/score")
    @ApiOperation("按成绩推荐")



    @PostMapping("/{userId}/score")
    @ApiOperation("按大学排名推荐")


    @PostMapping("/{userId}/score")
    @ApiOperation("综合推荐")
*/


}
