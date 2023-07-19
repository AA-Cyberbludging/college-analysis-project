package com.cyberbludging.cap.entity.dto;

public class RecommendDTO {

    private Integer uid;
    private String uname;
    private String probability;
    private String recommendType;
    private LowestMpsDTO lowestMps;

    public void set(Integer id,String name,String pro,String type){
        uid = id;
        uname = name;
        probability=pro;
        recommendType=type;
    }

    public Integer getUid() {
        return uid;
    }
    public String getUname(){
        return  uname;
    }
    public String getProbability(){
        return probability;
    }
    public String getRecommendType(){
        return recommendType;
    }

    public LowestMpsDTO getLowestMps() {
        return lowestMps;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public void setLowestMps(LowestMpsDTO lowestMpsList) {
        this.lowestMps = lowestMpsList;
    }
}
