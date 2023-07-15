package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.University;

import java.util.ArrayList;

public class UniCompare implements Filter{

    private String compare_obj;//分析对比的对象

    public UniCompare(String obj){
        this.compare_obj = obj;
        //初始化uni
    }

    /*男女比例对比分析*/
    public void gender(ArrayList<University> uni){

    }

    /*就业率对比分析*/
    public void employment(ArrayList<University> uni){

    }

    /*升学率对比分析*/
    public void progression(ArrayList<University> uni){

    }

    /*出国率对比分析*/
    public void abroad(ArrayList<University> uni){

    }
}
