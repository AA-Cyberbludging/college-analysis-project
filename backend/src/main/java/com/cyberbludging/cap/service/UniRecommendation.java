package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.University;

import java.util.ArrayList;

public class UniRecommendation {

    private ArrayList<University> rec_uni;

    public UniRecommendation(){
        rec_uni = new ArrayList<University>();
    }

    /*按位置推荐*/
    public ArrayList<University> recommend_ByPosition(ArrayList<University> uni){

        return this.rec_uni;
    }

    /*按成绩推荐*/
    public ArrayList<University> recommend_ByGrade(ArrayList<University> uni){

        return this.rec_uni;
    }

    /*按大学排名推荐*/
    public ArrayList<University> recommend_ByRank(ArrayList<University> uni){

        return this.rec_uni;
    }

    /*综合推荐*/
    public ArrayList<University> recommend_comprehensive(ArrayList<University> uni){

        return this.rec_uni;
    }
}
