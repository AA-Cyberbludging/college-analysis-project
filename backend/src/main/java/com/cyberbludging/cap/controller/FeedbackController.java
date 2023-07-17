package com.cyberbludging.cap.controller;

import com.cyberbludging.cap.entity.Feedback;
import com.cyberbludging.cap.service.FeedbackDealService;
import com.cyberbludging.cap.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "反馈接口")
@RestController
@RequestMapping("/feedback")
@CrossOrigin
public class FeedbackController {
    @Autowired
    private FeedbackDealService feedbackDealService;

    @GetMapping("")
    @ApiOperation("获取反馈列表")
    public List<Feedback> getAllFeedback() {
        return feedbackDealService.getAllFeedback();
    }

    @DeleteMapping("/{fd_id}")
    @ApiOperation("删除某条反馈")
    public Result deleteFeedback(@PathVariable Integer fd_id) {
        return feedbackDealService.deleteFeedback(fd_id);
    }

    @PostMapping("/{fd_id}")
    @ApiOperation("更改反馈状态")
    Result finishFeedback(@PathVariable Integer fd_id) {
        return feedbackDealService.finishFeedback(fd_id);
    }

    @PostMapping("/add")
    @ApiOperation("添加反馈")
    public Result addFeedback(@RequestBody Feedback feedback) {
        return feedbackDealService.addFeedback(feedback.getUserId(), feedback.getFeedback(), feedback.getTimestamp());
    }
}
