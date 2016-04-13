package com.example.jackadkins.whosit;

/**
 * Created by zman0_000 on 4/13/2016.
 */
public class User {
    private int userId;
    private String userName;
    private String password;

    User(String userN, String pass){
        makeNewUID();
        userName = userN;
        password = pass;
    }

    User(int uID, String userN, String pass){
        if(idInDatabase(uID)){
            makeNewUID();
        } else{
            userId = uID;
        }
        userName = userN;
        password = pass;
    }

    private boolean idInDatabase(int uID) {
        //Check if the ID is in the database and return true or false
        return false;
    }

    private void makeNewUID() {
        //Generate an ID that isn't already in the DB
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
    protected boolean setId(int uID){
        if(idInDatabase(uID)){
            return false;
        } else{
            userId = uID;
            return true;
        }
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
