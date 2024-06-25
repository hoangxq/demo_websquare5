package com.demo.dto;

import com.demo.entity.Gender;
import com.demo.entity.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private Gender gender;
    private String phone;
    private String address;
    private String team;
    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthDate;
}
