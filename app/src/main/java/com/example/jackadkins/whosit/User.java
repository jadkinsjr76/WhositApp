package com.example.jackadkins.whosit;

/**
 * Created by zman0_000 on 4/13/2016.
 */
public class User {
    private int userId;
    private String userName;
    private String password;

    User(String userN, String pass){
        userId = setId();
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
    private int setId(){
        //Get the ID from the DB
        return 0;
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