package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.Province;
import com.cyberbludging.cap.entity.ScoreTable;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.List;

@Service
public class UniRecommendationService {

    @Autowired
    private UniversityMapper universityMapper;

    private List<University> rec_uni = new ArrayList<University>();;
    private List<MinimumPassingScore> passingScoreList = new ArrayList<MinimumPassingScore>();
    private ScoreTable scoreTable = new ScoreTable();

    /*按位置推荐*/
    public List<University> recommend_ByPosition(ArrayList<University> uni, Province province){

        return this.rec_uni;
    }

    /*按成绩推荐
    *range表示推荐分数区间，录取最低分-range ~ 录取最低分+range
    */
    public List<University> recommend_ByGrade(Integer score, Integer range){
        for(Integer i=0;i<passingScoreList.size();i++){
            if(score<=passingScoreList.get(i).getUmps()+range &&
            score>=passingScoreList.get(i).getUmps()-range)
                rec_uni.add(universityMapper.getUniversityByID(passingScoreList.get(i).getUid()));
        }

        return this.rec_uni;

    }

    /*按大学排名推荐*/
    public List<University> recommend_ByRank(ArrayList<University> uni){

        return this.rec_uni;
    }

    /*综合推荐*/
    public List<University> recommend_comprehensive(Integer score, String provinceName, String major, String subject){

        return this.rec_uni;
    }

}
