package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.Feedback;
import com.cyberbludging.cap.entity.User;
import com.cyberbludging.cap.mapper.FeedbackMapper;
import com.cyberbludging.cap.mapper.UserMapper;
import com.cyberbludging.cap.util.Result;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

public class FeedbackDealService {
    private FeedbackMapper feedbackMapper;

    public List<Feedback> getAllFeedback() {
        return feedbackMapper.getAllFeedback();
    }

    public Result deleteFeedback(Integer id) {
        try {
            feedbackMapper.deleteFeedback(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().setMessage("反馈删除失败");
        }
    }

    public Result finishFeedback(Integer id) {
        try {
            feedbackMapper.finishFeedback(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().setMessage("反馈状态更新失败");
        }
    }

    public Result addFeedback(Integer feedbackId, Integer userId, String feedback, Time time) {
        try {
            feedbackMapper.addFeedback(feedbackId,userId,feedback,time);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().setMessage("添加反馈记录失败");
        }
    }
}
