package com.cyberbludging.cap.entity.dto;

public class MpsDTO {

    private Integer uid;
    private Integer averagePassingScore;
    private Integer averagePassingRank;

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setAveragePassingScore(Integer averagePassingScore) {
        this.averagePassingScore = averagePassingScore;
    }

    public void setAveragePassingRank(Integer averagePassingRank) {
        this.averagePassingRank = averagePassingRank;
    }

    public Integer getUid(){ return uid;}
    public Integer getAveragePassingScore(){ return averagePassingScore; }
    public Integer getAveragePassingRank(){ return averagePassingRank; }

}
