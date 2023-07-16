package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UniCompareService {


    @Autowired
    private UniversityMapper universityMapper;
    List<University> uni = new ArrayList<University>();


    /*两所大学对比
    *返回两所大学的University列表
     */
    public List<University> getRate(String uname1, String uname2){
        uni.add(universityMapper.getUniversityRateByName(uname1));
        uni.add(universityMapper.getUniversityRateByName(uname2));

        return uni;
    }

}
