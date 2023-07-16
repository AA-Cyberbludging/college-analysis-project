package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.User;
import com.cyberbludging.cap.mapper.UserMapper;
import com.cyberbludging.cap.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(Integer id) {
        return userMapper.getUserInfo(id);
    }

    public Result updateUserInfo(Integer id, String name, Integer score, String pname, String subject) {
        try {
            userMapper.updateUserInfo(id, name, score, pname, subject);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().setMessage("信息更新失败");
        }
    }
}
