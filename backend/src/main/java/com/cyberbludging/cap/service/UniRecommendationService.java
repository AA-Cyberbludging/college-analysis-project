package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.*;
import com.cyberbludging.cap.entity.dto.LowestMpsDTO;
import com.cyberbludging.cap.entity.dto.MpsDTO;
import com.cyberbludging.cap.entity.dto.RecommendDTO;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

@Service
public class UniRecommendationService {

    @Autowired
    private UniversityMapper universityMapper;

    private List<MpsDTO> mpsdto = new ArrayList<>();

    public MpsDTO getAvgByUniversity(User user, University uni) {
        MpsDTO mps = new MpsDTO();
        mpsdto = universityMapper.getAvg(user.getSubject(), user.getPname());
        for (int i = 0; i < mpsdto.size(); i++) {
            if (uni.getUid().equals(mpsdto.get(i).getUid())) {
                mps = mpsdto.get(i);
            }
        }
        return mps;
    }

    public double getProbability(User user, University uni) {


        double scoreProbability = calculateScoreProbability((double) user.getUserScore(), uni, user);
        double rankProbability = calculateRankProbability(user.getUserRank(), uni, user);

        double weightScore = 0.35; // 分数的权重
        double weightRank = 0.65; // 位次的权重
        double probability = Math.sqrt(weightScore * scoreProbability * scoreProbability + weightRank * rankProbability * rankProbability);

        return probability;
    }

    public double calculateScoreProbability(double score, University uni, User user) {
        // 使用正态分布计算分数的概率
        double meanScore = getAvgByUniversity(user, uni).getAveragePassingScore(); // 分数的均值
        double stdDeviationScore = 10.0; // 分数的标准差

        // 使用正态分布计算分数的概率密度函数值
        NormalDistribution scoreDistribution = new NormalDistribution(meanScore, stdDeviationScore);
        double scoreProbability = scoreDistribution.cumulativeProbability(score);

        return scoreProbability;
    }

    public double calculateRankProbability(int rank, University uni, User user) {
        // 使用正态分布计算位次的概率
        double meanRank = getAvgByUniversity(user, uni).getAveragePassingRank();// 位次的均值
        double stdDeviationRank = 100.0; // 位次的标准差

        // 计算位次的概率，越低的位次应该对应越高的概率
        NormalDistribution rankDistribution = new NormalDistribution(meanRank, stdDeviationRank);
        double rankProbability = 1.0 - rankDistribution.cumulativeProbability(rank);

        return rankProbability;
    }


    /*根据考生成绩、文理科推荐院校
     *目前录取概率0.1~0.4为冲，0.4~0.8为稳，0.8以上为保
     */
    public List<RecommendDTO> recommend(User user) {
        List<RecommendDTO> recommendDTOS = new ArrayList<>();
        mpsdto = universityMapper.getAvg(user.getSubject(), user.getPname());
        for (int i = 0; i < mpsdto.size(); i++) {
            RecommendDTO recommend = new RecommendDTO();
            double pro = getProbability(user, universityMapper.getUniversityByID(mpsdto.get(i).getUid()));
            LowestMpsDTO lowestMps =
                    universityMapper.getLowestMpsByConditions(mpsdto.get(i).getUid(), 2022, user.getSubject(), user.getPname());

            if (pro >= 0.1 && pro < 0.4) {
                recommend.set(mpsdto.get(i).getUid(),
                        universityMapper.getUniversityByID(mpsdto.get(i).getUid()).getUname(),
                        getPercentProbability(pro), "冲");
                recommend.setLowestMps(lowestMps);
                recommendDTOS.add(recommend);
            } else if (pro >= 0.4 && pro < 0.8) {
                recommend.set(mpsdto.get(i).getUid(),
                        universityMapper.getUniversityByID(mpsdto.get(i).getUid()).getUname(),
                        getPercentProbability(pro), "稳");
                recommend.setLowestMps(lowestMps);
                recommendDTOS.add(recommend);
            } else if (pro >= 0.8) {
                recommend.set(mpsdto.get(i).getUid(),
                        universityMapper.getUniversityByID(mpsdto.get(i).getUid()).getUname(),
                        getPercentProbability(pro), "保");
                recommend.setLowestMps(lowestMps);
                recommendDTOS.add(recommend);
            }
        }
        return recommendDTOS;
    }

    /*
    转化录取率为百分制字符串
     */
    public String getPercentProbability(double num) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);
        percentFormat.setMaximumFractionDigits(2);

        return percentFormat.format(num);
    }
}
