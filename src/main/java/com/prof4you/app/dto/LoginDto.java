package com.prof4you.app.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class LoginDto {


    @NotNull
    private String email;

    @NotNull
    private String password;


    private Boolean rememberMe;
}