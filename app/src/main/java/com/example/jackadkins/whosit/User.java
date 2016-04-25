package com.example.jackadkins.whosit;

/**
 * Created by zman0_000 on 4/13/2016.
 */
public class User {
    private int userId;
    private String userName;
    private String password;

    User(String userN, String pass){
        userName = userN;
        password = pass;
    }

    User(int id, String userN, String pass){
        userId = id;
        userName = userN;
        password = pass;
    }

    protected int getId(){
        return userId;
    }
    protected String getUserName(){
        return userName;
    }
    protected String getPassword(){
        return password;
    }
    protected void setId(int id){
        userId = id;
    }
    protected boolean changePassword(String oldPass, String newPass){
        if(oldPass.equals(password)){
            password = newPass;
            return true;
        } else{
            return false;
        }
    }
}