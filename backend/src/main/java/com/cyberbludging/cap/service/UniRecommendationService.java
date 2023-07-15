package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.Province;
import com.cyberbludging.cap.entity.ScoreTable;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.stereotype.Service;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;

@Service
public class UniRecommendationService {

    private UniversityMapper universityMapper;
    private ArrayList<University> rec_uni = new ArrayList<University>();;
    private ArrayList<MinimumPassingScore> minimumPassingScore = new ArrayList<MinimumPassingScore>();
    private ScoreTable scoreTable = new ScoreTable();

    public UniRecommendationService(){
        //初始化minimumPassingScore
    }

    /*按位置推荐*/
    public ArrayList<University> recommend_ByPosition(ArrayList<University> uni, Province province){

        return this.rec_uni;
    }

    /*按成绩推荐*/
    public ArrayList<University> recommend_ByGrade(ArrayList<University> uni, ScoreTable score){

        return this.rec_uni;
    }

    /*按大学排名推荐*/
    public ArrayList<University> recommend_ByRank(ArrayList<University> uni){

        return this.rec_uni;
    }

    /*综合推荐*/
    public ArrayList<University> recommend_comprehensive(ArrayList<University> uni, ScoreTable score, Province province){

        return this.rec_uni;
    }

}
