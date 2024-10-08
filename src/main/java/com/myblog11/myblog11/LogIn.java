package com.myblog11.myblog11;

public class LogIn {


    private String UserName;
    private  String Password;


    public LogIn(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return UserName;
    }


}
