package com.cyberbludging.cap.entity.dto;

public class UniversityDTO {
    private Integer uid;

    private String uname;

    private String pname;

    private String utype;

    private Integer upopularity;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public Integer getUpopularity() {
        return upopularity;
    }

    public void setUpopularity(Integer upopularity) {
        this.upopularity = upopularity;
    }

    @Override
    public String toString() {
        return "UniversityDTO{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pname='" + pname + '\'' +
                ", utype='" + utype + '\'' +
                ", upopularity=" + upopularity +
                '}';
    }
}
