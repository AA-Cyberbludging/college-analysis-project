package com.cyberbludging.cap.entity;

public class enrollment_plan {
    private Integer uid;
    private String pname;
    private String subject;
    private String major;
    private Integer year;
    private Integer enrollment_num;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getEnrollment_num() {
        return enrollment_num;
    }

    public void setEnrollment_num(Integer enrollment_num) {
        this.enrollment_num = enrollment_num;
    }

    @Override
    public String toString() {
        return "enrollment_plan{" +
                "uid=" + uid +
                ", pname='" + pname + '\'' +
                ", subject='" + subject + '\'' +
                ", major='" + major + '\'' +
                ", year=" + year +
                ", enrollment_num=" + enrollment_num +
                '}';
    }
}
