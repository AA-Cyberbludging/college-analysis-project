package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.University;

import java.util.*;
public class AnalyseInfo implements Filter{

    public AnalyseInfo(){
        //初始化uni
    }

    @Override
    public void filter(String obj, ArrayList<University> uni) {
        Filter.super.filter(obj, uni);
    }

    /*展示录取趋势*/
    public void show_AdmissionTrends(ArrayList<University> uni){

    }

    /*展示招生分布*/
    public void show_AdmissionDistribution(ArrayList<University> uni){

    }
}
