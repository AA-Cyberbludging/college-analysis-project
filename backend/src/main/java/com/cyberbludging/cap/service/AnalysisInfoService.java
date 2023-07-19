package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.EnrollmentPlan;
import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalysisInfoService {

    @Autowired
    private UniversityMapper universityMapper;

    /*获取某大学各专业录取分数线及位次
    *返回MinimumPassingScore列表
     */
    public List<MinimumPassingScore> getMpsById(Integer id) {
        return universityMapper.getMpsById(id);
    }

    /*返回某大学的招生计划
    *返回EnrollmentPlan列表
     */
    public List<EnrollmentPlan> getEnrollmentPlan(Integer id){
        return universityMapper.getEnrollmentPlanById(id);
    }
}
