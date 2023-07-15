package com.cyberbludging.cap.entity;

public class province {
    private String pname;
    private Integer unum;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getUnum() {
        return unum;
    }

    public void setUnum(Integer unum) {
        this.unum = unum;
    }

    @Override
    public String toString() {
        return "province{" +
                "pname='" + pname + '\'' +
                ", unum=" + unum +
                '}';
    }
}
