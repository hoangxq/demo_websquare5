package com.demo.dto.request;

import com.demo.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserRequest toUserRequest(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(user.getId());
        userRequest.setName(user.getName());
        userRequest.setEmail(user.getEmail());
        userRequest.setBirthDate(user.getBirthDate());
        userRequest.setGender(user.getGender());
        userRequest.setPhone(user.getPhone());
        userRequest.setAddress(user.getAddress());
        userRequest.setStatus(user.getStatus());
        userRequest.setTeam(user.getTeam() != null ? user.getTeam().getName() : null);
        return userRequest;
    }

    public static List<UserRequest> toUserRequests(List<User> users) {
        return users.stream()
                .map(UserMapper::toUserRequest)
                .collect(Collectors.toList());
    }
}
