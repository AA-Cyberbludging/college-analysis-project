package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.User;
import com.cyberbludging.cap.mapper.UserMapper;
import com.cyberbludging.cap.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public Result deleteUser(Integer id) {
        try {
            userMapper.deleteUser(id);
            return Result.ok();
        } catch (Exception e) {
            return Result.error().setMessage("用户删除失败");
        }
    }
}
