package com.demo.dto.request;

import com.demo.entity.Gender;
import com.demo.entity.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UpsertUserReq {
    private Integer id = -1;
    private String name;
    private String email;
    private Gender gender;
    private String phone;
    private String address;
    private Integer team;
    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
}
