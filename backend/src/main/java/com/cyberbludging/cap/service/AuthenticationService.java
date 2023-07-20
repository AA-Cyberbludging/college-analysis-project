package com.cyberbludging.cap.service;

import com.cyberbludging.cap.mapper.UserMapper;
import com.cyberbludging.cap.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserMapper userMapper;

    public Result login(String name, String password, Boolean isStudent) {
        Integer type = isStudent ? 1 : 2;
        if (userMapper.verifyUser(name, password, type) == 1) {
            Integer id = userMapper.getUserId(name);
            return Result.ok().addData("id", id);
        } else {
            return Result.error().setMessage("账号或密码错误");
        }
    }

    public Result register(String name, String password, String pname, String subject, Integer userScore, Integer userRank) {
        try {
            userMapper.registerUser(name, password, pname, subject, userScore, userRank);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().setMessage("注册失败，用户名可能已被占用");
        }
    }
}
