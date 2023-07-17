package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.Feedback;
import com.cyberbludging.cap.entity.User;
import com.cyberbludging.cap.mapper.FeedbackMapper;
import com.cyberbludging.cap.mapper.UserMapper;
import com.cyberbludging.cap.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

@Service
public class FeedbackDealService {
    @Autowired
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

    public Result addFeedback(Integer userId, String feedback, Long timestamp) {
        try {
            feedbackMapper.addFeedback(userId, feedback, timestamp);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().setMessage("添加反馈记录失败");
        }
    }
}
