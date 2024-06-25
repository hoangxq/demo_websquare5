package com.demo.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UserListRemove {
    private List<Integer> listUser;
}
