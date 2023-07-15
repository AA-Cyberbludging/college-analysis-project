package com.cyberbludging.cap.entity;

public class ScoreTable {
    private String pname;
    private String subject;
    private Integer year;
    private Integer score;
    private Integer count;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "score_table{" +
                "pname='" + pname + '\'' +
                ", subject='" + subject + '\'' +
                ", year=" + year +
                ", score=" + score +
                ", count=" + count +
                '}';
    }
}
