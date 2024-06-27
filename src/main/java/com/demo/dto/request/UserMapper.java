package com.demo.dto.request;

import com.demo.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBirthDate(user.getBirthDate());
        userResponse.setGender(user.getGender());
        userResponse.setPhone(user.getPhone());
        userResponse.setAddress(user.getAddress());
        userResponse.setStatus(user.getStatus());
        userResponse.setTeam(user.getTeam() != null ? user.getTeam().getName() : null);
        return userResponse;
    }

    public static List<UserResponse> toUserResponse(List<User> users) {
        return users.stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public static Page<UserResponse> toUserResponse(Page<User> users) {
        return users.map(UserMapper::toUserResponse);
    }
}
