package com.cyberbludging.cap.controller;

import com.cyberbludging.cap.entity.Feedback;
import com.cyberbludging.cap.service.FeedbackDealService;
import com.cyberbludging.cap.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

public class FeedbackController {
    @Autowired
    private FeedbackDealService feedbackDealService;

    @GetMapping("/feedback")
    @ApiOperation("获取反馈列表")
    public List<Feedback> getAllFeedback() {
        return feedbackDealService.getAllFeedback();
    }

    @DeleteMapping("/feedback")
    @ApiOperation("删除某条反馈")
    public Result dropCourse(@PathVariable Integer fd_id) {
        return feedbackDealService.deleteFeedback(fd_id);
    }

    @PostMapping("/feedback/{fd_id}")
    @ApiOperation("更改反馈状态")
    Result finishFeedback(@PathVariable Integer fd_id) {
        return feedbackDealService.finishFeedback(fd_id);
    }

    @PostMapping("/feedback/add")
    @ApiOperation("添加反馈")
    public Result addFeedback(Integer feedbackId, Integer userId, String feedback, Time time) {
        try {
            feedbackDealService.addFeedback(feedbackId, userId, feedback, time);
            return Result.ok().setMessage("感谢您的反馈!");
        } catch (Exception e) {
            return Result.error().setMessage("反馈失败");
        }
    }
}
