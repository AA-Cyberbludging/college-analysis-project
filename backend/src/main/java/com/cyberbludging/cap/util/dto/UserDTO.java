package com.cyberbludging.cap.util.dto;

public class UserDTO {
    private Integer id;

    private String password;

    private Boolean isUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsUser() {
        
        return isUser;
    }

    public void setIsStudent(Boolean user) {
        
        isUser = user;
    }
}
