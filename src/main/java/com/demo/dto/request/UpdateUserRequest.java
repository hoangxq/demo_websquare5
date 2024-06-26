package com.demo.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserRequest {
    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private String gender;
    private String phone;
    private String address;
    private String team;
    private String status;
}
