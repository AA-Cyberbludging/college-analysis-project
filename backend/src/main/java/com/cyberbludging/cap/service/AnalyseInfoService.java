package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.EnrollmentPlan;
import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyseInfoService {

    @Autowired
    private UniversityMapper universityMapper;

    private List<MinimumPassingScore> minimumPassingScores = new ArrayList<MinimumPassingScore>();

    /*获取某大学各专业录取分数线及位次
    *返回MinimumPassingScore列表
     */
    public  List<MinimumPassingScore> getMPSByuName(String uname){
        minimumPassingScores = universityMapper.getMPSByuName(uname);
        return minimumPassingScores;
    }


}
