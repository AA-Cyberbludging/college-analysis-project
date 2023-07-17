package com.cyberbludging.cap.entity;

public class Feedback {
    private Integer fbId;

    private Integer userId;

    private String feedback;

    private Long timestamp;

    private Boolean fbDeal;

    // 以下属性未出现在表中

    private String userName;

    public Integer getFbId() {
        return fbId;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getFbDeal() {
        return fbDeal;
    }

    public void setFbDeal(Boolean fbDeal) {
        this.fbDeal = fbDeal;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + fbId +
                ", userId=" + userId +
                ", feedback='" + feedback + '\'' +
                ", time=" + timestamp +
                ", feedbackDeal=" + fbDeal +
                '}';
    }
}
