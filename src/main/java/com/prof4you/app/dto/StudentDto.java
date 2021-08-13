package com.prof4you.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDto {


    private String firstname;

    private String lastname;

    private Date birthDay;

    private String address;

    private String phoneNumber;

    private String photo;
}
