package com.cyberbludging.cap.entity;

import java.sql.Time;

public class Feedback {
    private Integer feedbackId;

    private Integer userId;

    private String feedback;

    private Time time;

    private Boolean feedbackDeal;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Boolean getFeedbackDeal() {
        return feedbackDeal;
    }

    public void setFeedbackDeal(Boolean feedbackDeal) {
        this.feedbackDeal = feedbackDeal;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", userId=" + userId +
                ", feedback='" + feedback + '\'' +
                ", time=" + time +
                ", feedbackDeal=" + feedbackDeal +
                '}';
    }
}
