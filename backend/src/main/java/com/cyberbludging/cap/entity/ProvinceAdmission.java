package com.cyberbludging.cap.entity;

public class ProvinceAdmission {
    private String pname;
    private Integer year;
    private Integer admissionLine;
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

    public Integer getAdmissionLine() {
        return admissionLine;
    }

    public void setAdmissionLine(Integer admissionLine) {
        this.admissionLine = admissionLine;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
