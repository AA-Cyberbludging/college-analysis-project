package com.cyberbludging.cap.entity;

public class ProvinceAdmission {
    private String pname;
    private Integer year;
    private Integer admission_line;
    private String subject;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAdmission_line() {
        return admission_line;
    }

    public void setAdmission_line(Integer admission_line) {
        this.admission_line = admission_line;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "province_admission{" +
                "pname='" + pname + '\'' +
                ", year=" + year +
                ", admission_line=" + admission_line +
                ", subject='" + subject + '\'' +
                '}';
    }
}
