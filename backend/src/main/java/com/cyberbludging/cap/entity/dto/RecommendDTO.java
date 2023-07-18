package com.cyberbludging.cap.entity.dto;

public class RecommendDTO {

    private Integer uid;
    private String uname;
    private String probability;
    private String recommendType;

    public void set(Integer id,String name,String pro,String type){
        uid = id;
        uname = name;
        probability=pro;
        recommendType=type;
    }
    /*public void setUid(Integer id)  {uid = id;}
    public void setUname(String name)   {uname = name;}
    public void setProbability(String pro)  {probability=pro;}
    public void setRecommendType(String type)   {recommendType=type;}*/

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
}
