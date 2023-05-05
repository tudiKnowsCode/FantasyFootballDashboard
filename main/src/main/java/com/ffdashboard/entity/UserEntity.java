package com.ffdashboard.entity;

public class UserEntity {

    private String userName;

    private String userId;

    public UserEntity(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }
    
    public String toString(){//overriding the toString() method  
        return userName;  
       }  

}
