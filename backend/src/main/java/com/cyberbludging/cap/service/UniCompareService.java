package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UniCompareService {

    private String compare_obj;//分析对比的对象
    private UniversityMapper universityMapper;

    public UniCompareService(String obj){
        this.compare_obj = obj;

    }

    /*男女比例对比分析*/
    public void sexRatioCompare(ArrayList<University> uni){

    }

    /*就业率对比分析*/
    public void employRateCompare(ArrayList<University> uni){

    }

    /*升学率对比分析*/
    public void progression(ArrayList<University> uni){

    }

    /*出国率对比分析*/
    public void abroad(ArrayList<University> uni){

    }
}
