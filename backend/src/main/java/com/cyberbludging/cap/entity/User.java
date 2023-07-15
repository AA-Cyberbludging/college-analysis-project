package com.cyberbludging.cap.entity;

public class User {
    private Integer user_id;
    private String user_name;
    private String user_pswd;
    private Integer user_type;
    private Integer user_score;
    private Integer user_rank;
    private String pname;
    private String subject;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pswd() {
        return user_pswd;
    }

    public void setUser_pswd(String user_pswd) {
        this.user_pswd = user_pswd;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getUser_score() {
        return user_score;
    }

    public void setUser_score(Integer user_score) {
        this.user_score = user_score;
    }

    public Integer getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(Integer user_rank) {
        this.user_rank = user_rank;
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

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_pswd='" + user_pswd + '\'' +
                ", user_type=" + user_type +
                ", user_score=" + user_score +
                ", user_rank=" + user_rank +
                ", pname='" + pname + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

