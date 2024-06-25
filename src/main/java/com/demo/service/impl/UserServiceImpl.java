package com.demo.service.impl;

import com.demo.dto.UserDto;
import com.demo.dto.request.RemoveUsersReq;
import com.demo.dto.request.UserCriteria;
import com.demo.dto.utils.PagingReq;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.MappingHelper;
import com.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MappingHelper mappingHelper;

    @Override
    public Page<?> getUsers(UserCriteria userCriteria, PagingReq pagingReq) {
        return userRepository.findAll(userCriteria == null ? null : userCriteria.toSpecification(), pagingReq.makePageable())
                .map(e -> {
                    var res = mappingHelper.map(e, UserDto.class);
                    res.setTeam(e.getTeam().getName());
                    return res;
                });
    }

    @Override
    public void upsertUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUsers(RemoveUsersReq removeUsersReq) {
        userRepository.deleteAllById(removeUsersReq.getUserIds());
    }

    @Override
    public List<?> exportDataUsers(UserCriteria userCriteria) {
        return userRepository.findAll(userCriteria == null ? null : userCriteria.toSpecification());
    }
}
