package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.*;
import com.cyberbludging.cap.entity.dto.MPSDTO;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

@Service
public class UniRecommendationService {

    @Autowired
    private UniversityMapper universityMapper;

    enum recommendType{
        upper,middle,lower
    }
    private recommendType recType;
    private double probability;
    private List<MPSDTO> mpsdto = new ArrayList<MPSDTO>();
    private List<University> universities = new ArrayList<University>();


    public UniRecommendationService(){
        mpsdto = universityMapper.getAvg();
    }

    private boolean inUpperRange(Integer score, Integer rank, MPSDTO mps){
        if((score > mps.getAveragePassingScore()-20 && score <= mps.getAveragePassingScore()-10) ||
                (rank > mps.getAveragePassingRank()-2000 && rank <= mps.getAveragePassingRank()-1000)){
            return true;
        }
        return false;
    }
    private boolean inMiddleRange(Integer score, Integer rank, MPSDTO mps){
        if((score > mps.getAveragePassingScore()-10 && score <= mps.getAveragePassingScore()+10) ||
                (rank > mps.getAveragePassingRank()-1000 && rank <= mps.getAveragePassingRank()+1000)){
            return true;
        }
        return false;
    }
    private boolean inLowerRange(Integer score, Integer rank, MPSDTO mps){
        if((score > mps.getAveragePassingScore()+10 && score <= mps.getAveragePassingScore()+20) ||
                (rank > mps.getAveragePassingRank()+1000 && rank <= mps.getAveragePassingRank()+2000)){
            return true;
        }
        return false;
    }
    private double singleProbability(Integer temp, Integer avg){
        double result = (double)avg/temp;
        double t =result;
        for(int i=0;i<16;i++){
            result = result*t;
        }
        return 1-0.5*result;
    }

    //冲
    public List<University> recommendUpper(User user){

        List<University> uni = new ArrayList<University>();
        List<MPSDTO> mps = mpsdto;
        this.recType = recommendType.upper;
        for(int i=0;i< mps.size();i++){
            if(inUpperRange(user.getUserScore(),user.getUserRank(), mps.get(i))){
                uni.add(universityMapper.getUniversityByID(mps.get(i).getUid()));
            }
        }
        universities = uni;
        return this.universities;

    }

    //稳
    public List<University> recommendMiddle(User user) {

        List<University> uni = new ArrayList<University>();
        List<MPSDTO> mps = mpsdto;
        this.recType = recommendType.middle;
        for(int i=0;i< mps.size();i++){
            if(inMiddleRange(user.getUserScore(),user.getUserRank(), mps.get(i))){
                uni.add(universityMapper.getUniversityByID(mps.get(i).getUid()));
            }
        }
        universities = uni;
        return this.universities;
    }

    //保
    public List<University> recommendLower(User user){

        List<University> uni = new ArrayList<University>();
        List<MPSDTO> mps = mpsdto;
        this.recType = recommendType.lower;
        for(int i=0;i< mps.size();i++){
            if(inLowerRange(user.getUserScore(),user.getUserRank(), mps.get(i))){
                uni.add(universityMapper.getUniversityByID(mps.get(i).getUid()));
            }
        }
        universities = uni;
        return this.universities;
    }

    public MPSDTO getAvgByUniversity(University uni){
        MPSDTO mps = new MPSDTO();
        for (int i=0;i< mpsdto.size();i++){
            if(uni.getUid().equals(mpsdto.get(i).getUid())){
                mps = mpsdto.get(i);
            }
        }
        return mps;
    }

    public String getRecommendType(){

        return this.recType.toString();
    }

    /*按照score与rank分别占比25%和75%
    *在考生成绩与近三年平均相等时录取概率为0.5
    * 相应地，考生成绩较低则录取概率低于0.5，较高高于0.5
     */
    public double getProbability(User user, University uni){

        double scoreProbability, rankProbability;
        if(user.getUserScore()>=getAvgByUniversity(uni).getAveragePassingScore()){
            scoreProbability = singleProbability(user.getUserScore(),
                    getAvgByUniversity(uni).getAveragePassingScore());
        }
        else {
            scoreProbability = 1-singleProbability(getAvgByUniversity(uni).getAveragePassingScore(),
                    user.getUserScore());
        }
        if(user.getUserRank()>=getAvgByUniversity(uni).getAveragePassingRank()){
            rankProbability = singleProbability(user.getUserRank(),
                    getAvgByUniversity(uni).getAveragePassingRank());
        }
        else {
            rankProbability = 1-singleProbability(getAvgByUniversity(uni).getAveragePassingRank(),
                    user.getUserRank());;
        }
        probability = Math.sqrt(0.25*scoreProbability*scoreProbability+0.75*rankProbability*rankProbability);
        return this.probability;
    }

    /*
    转化录取率为百分制字符串
     */
    public String getPercentProbability(double num){
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);
        percentFormat.setMaximumFractionDigits(2);

        return percentFormat.format(num);
    }

}
