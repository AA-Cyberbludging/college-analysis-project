package com.cyberbludging.cap.service;

import com.cyberbludging.cap.mapper.UniversityMapper;
import com.cyberbludging.cap.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    private UniversityMapper universityMapper;

    @Transactional
    public Result login(Integer id, String password, Integer userType){

        return null;
    }

}
