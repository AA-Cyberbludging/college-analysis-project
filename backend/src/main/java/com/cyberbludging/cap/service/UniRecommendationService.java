package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.*;
import com.cyberbludging.cap.entity.dto.MPSDTO;
import com.cyberbludging.cap.entity.dto.RecommendDTO;
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

    private double probability;
    private List<MPSDTO> mpsdto = new ArrayList<MPSDTO>();
    private List<University> universities = new ArrayList<University>();
    private List<RecommendDTO> recommendDTOS = new ArrayList<RecommendDTO>();


    private double singleProbability(Integer temp, Integer avg){
        double result = (double)avg/temp;
        double t =result;
        for(int i=0;i<16;i++){
            result = result*t;
        }
        return 1-0.5*result;
    }

    public MPSDTO getAvgByUniversity(User user,University uni){
        MPSDTO mps = new MPSDTO();
        mpsdto = universityMapper.getAvg(user.getSubject(),user.getPname());
        for (int i=0;i< mpsdto.size();i++){
            if(uni.getUid().equals(mpsdto.get(i).getUid())){
                mps = mpsdto.get(i);
            }
        }
        return mps;
    }

    /*按照score与rank分别占比25%和75%
    *在考生成绩与近三年平均相等时录取概率为0.5
    * 相应地，考生成绩较低则录取概率低于0.5，较高高于0.5
     */
    public double getProbability(User user, University uni){
        double scoreProbability, rankProbability;
        if(user.getUserScore()>=getAvgByUniversity(user,uni).getAveragePassingScore()){
            scoreProbability = singleProbability(user.getUserScore(),
                    getAvgByUniversity(user,uni).getAveragePassingScore());
        }
        else {
            scoreProbability = 1-singleProbability(getAvgByUniversity(user,uni).getAveragePassingScore(),
                    user.getUserScore());
        }
        if(user.getUserRank()>=getAvgByUniversity(user,uni).getAveragePassingRank()){
            rankProbability = 1-singleProbability(user.getUserRank(),
                    getAvgByUniversity(user,uni).getAveragePassingRank());
        }
        else {
            rankProbability = singleProbability(getAvgByUniversity(user,uni).getAveragePassingRank(),
                    user.getUserRank());;
        }
        probability = Math.sqrt(0.25*scoreProbability*scoreProbability+0.75*rankProbability*rankProbability);
        return this.probability;
    }

    /*根据考生成绩、文理科推荐院校
     *目前录取概率0.2~0.4为冲，0.4~0.7为稳，0.7以上为保
     */
    public List<RecommendDTO> recommend(User user){
        mpsdto = universityMapper.getAvg(user.getSubject(),user.getPname());
        for(int i=0;i<mpsdto.size();i++){
            RecommendDTO recommend = new RecommendDTO();
            double pro=getProbability(user,universityMapper.getUniversityByID(mpsdto.get(i).getUid()));

            if(pro>=0.2 && pro<0.4){
                recommend.set(mpsdto.get(i).getUid(),
                        universityMapper.getUniversityByID(mpsdto.get(i).getUid()).getUname(),
                        getPercentProbability(pro), "冲");
                recommendDTOS.add(recommend);
            }
            else if(pro>=0.4 && pro <0.7){
                recommend.set(mpsdto.get(i).getUid(),
                        universityMapper.getUniversityByID(mpsdto.get(i).getUid()).getUname(),
                        getPercentProbability(pro),"稳");
                recommendDTOS.add(recommend);
            }
            else if(pro>=0.7){
                recommend.set(mpsdto.get(i).getUid(),
                        universityMapper.getUniversityByID(mpsdto.get(i).getUid()).getUname(),
                        getPercentProbability(pro),"保");
                recommendDTOS.add(recommend);
            }
        }
        return recommendDTOS;
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
