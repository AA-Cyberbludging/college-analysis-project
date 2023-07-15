package com.cyberbludging.cap.entity;

public class mps {
    private Integer uid;
    private Integer year;
    private String major;
    private String subject;
    private Integer umps;
    private Integer rank;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getUmps() {
        return umps;
    }

    public void setUmps(Integer umps) {
        this.umps = umps;
    }

    @Override
    public String toString() {
        return "mps{" +
                "uid=" + uid +
                ", year=" + year +
                ", major='" + major + '\'' +
                ", subject='" + subject + '\'' +
                ", umps=" + umps +
                ", rank=" + rank +
                '}';
    }
}
