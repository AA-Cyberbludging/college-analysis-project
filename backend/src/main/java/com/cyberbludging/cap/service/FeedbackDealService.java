package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.Feedback;
import com.cyberbludging.cap.mapper.UserMapper;
import com.cyberbludging.cap.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackDealService {
    @Autowired
    private UserMapper userMapper;

    public List<Feedback> getAllFeedback() {
        return userMapper.getAllFeedback();
    }

    public Result deleteFeedback(Integer id) {
        try {
            userMapper.deleteFeedback(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().setMessage("反馈删除失败");
        }
    }

    public Result finishFeedback(Integer id) {
        try {
            userMapper.finishFeedback(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().setMessage("反馈状态更新失败");
        }
    }

    public Result addFeedback(Integer userId, String feedback, Long timestamp) {
        try {
            userMapper.addFeedback(userId, feedback, timestamp);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().setMessage("添加反馈记录失败");
        }
    }
}
