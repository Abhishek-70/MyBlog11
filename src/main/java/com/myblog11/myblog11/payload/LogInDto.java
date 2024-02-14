package com.myblog11.myblog11.payload;

import lombok.Data;

@Data
public class LogInDto{
    private  String UserNameOrEmail;
    private String Password;

}
