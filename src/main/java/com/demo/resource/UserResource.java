package com.demo.resource;

import com.demo.dto.request.*;
import com.demo.dto.utils.PagingReq;
import com.demo.dto.utils.ResponseUtils;
import com.demo.entity.Team;
import com.demo.entity.User;
import com.demo.repository.TeamRepository;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserResource {
    private final UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    private User convertToEntity(CreateUserRequest createUserRequest){
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        user.setBirthDate(createUserRequest.getBirthDate());
        user.setGender(createUserRequest.getGender());
        user.setPhone(createUserRequest.getPhone());
        user.setAddress(createUserRequest.getAddress());
        user.setStatus(createUserRequest.getStatus());

        if (createUserRequest.getTeam() != null && !createUserRequest.getTeam().isEmpty()) {
            int teamId = Integer.parseInt(createUserRequest.getTeam());
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new RuntimeException("Team not found: " + teamId));
            user.setTeam(team);
        }

        return user;
    }

    private User updateUserEntity(User user, UpdateUserRequest updateUserRequest) {
        user.setName(updateUserRequest.getName());
        user.setEmail(updateUserRequest.getEmail());
        user.setBirthDate(updateUserRequest.getBirthDate());
        user.setGender(updateUserRequest.getGender());
        user.setPhone(updateUserRequest.getPhone());
        user.setAddress(updateUserRequest.getAddress());
        user.setStatus(updateUserRequest.getStatus());

        if (updateUserRequest.getTeam() != null && !updateUserRequest.getTeam().isEmpty()) {
            int teamId = Integer.parseInt(updateUserRequest.getTeam());
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new RuntimeException("Team not found: " + teamId));
            user.setTeam(team);
        }

        return user;
    }

    @PostMapping("/get-all-user")
    public ResponseEntity<?> getAllUser(@RequestBody(required = false) UserCriteria userCriteria) {
        List<User> users = userRepository.findAll(userCriteria == null ? null : userCriteria.toSpecification());
        List<UserRequest> userRequests = users.stream()
                .map(UserMapper::toUserRequest)
                .collect(Collectors.toList());
        return ResponseUtils.ok(userRequests);
    }

    @PostMapping("")
    public ResponseEntity<?> getUsers(@RequestBody(required = false) UserCriteria userCriteria, @Validated PagingReq pagingReq) {
        List<User> users = userRepository.findAll(userCriteria == null ? null : userCriteria.toSpecification(), pagingReq.makePageable()).getContent();
        List<UserRequest> userRequests = UserMapper.toUserRequests(users);
        return ResponseUtils.ok(userRequests);
    }

    @PostMapping("/export-excel")
    public ResponseEntity<?> getUsers(@RequestBody(required = false) UserCriteria userCriteria) {
        List<User> users = userRepository.findAll(userCriteria == null ? null : userCriteria.toSpecification());
        List<UserRequest> userRequests = UserMapper.toUserRequests(users);
        return ResponseUtils.ok(userRequests);
    }

    @PostMapping("/save-user")
    public ResponseEntity<?> saveUser(@RequestBody CreateUserRequest userRequest) {
        User user = convertToEntity(userRequest);
        User savedUser = userRepository.save(user);
        return ResponseUtils.ok(savedUser);
    }

    @PostMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        Optional<User> optionalUser = userRepository.findById(updateUserRequest.getId());
        if (!optionalUser.isPresent()) {
            return ResponseUtils.notFound("User not found with id: " + updateUserRequest.getId());
        }

        User user = optionalUser.get();
        user = updateUserEntity(user, updateUserRequest);
        User updatedUser = userRepository.save(user);
        return ResponseUtils.ok(updatedUser);
    }


    @DeleteMapping("/remove-list-user")
    public ResponseEntity<?> removeListUser(@RequestBody UserListRemove userListRemove) {
        userRepository.deleteAllById(userListRemove.getListUser());
        return ResponseUtils.ok();
    }

//    @GetMapping("/fake-data")
//    public ResponseEntity<?> fakeData() {
//        List<User> users = new ArrayList<>();
//
//        LocalDate start = LocalDate.of(1989, 10, 14);
//        LocalDate end = LocalDate.now();
//
//        for (int i = 0; i < 300; i++) {
//            users.add(new User(
//                    null,
//                    getSaltString(),
//                    getSaltString() + "@gmail.com",
//                    Date.from(between(start, end).atStartOfDay(ZoneId.systemDefault()).toInstant()),
//                    getSaltString(),
//                    getSaltString(),
//                    getSaltString(),
//                    getSaltString(),
//                    getSaltString()
//            ));
//        }
//        userRepository.saveAll(users);
//        return ResponseUtils.ok(users);
//    }
//
//    protected String getSaltString() {
//        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder salt = new StringBuilder();
//        Random rnd = new Random();
//        while (salt.length() < 18) { // length of the random string.
//            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//            salt.append(SALTCHARS.charAt(index));
//        }
//        String saltStr = salt.toString();
//        return saltStr;
//    }
//
//    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
//        long startEpochDay = startInclusive.toEpochDay();
//        long endEpochDay = endExclusive.toEpochDay();
//        long randomDay = ThreadLocalRandom
//                .current()
//                .nextLong(startEpochDay, endEpochDay);
//
//        return LocalDate.ofEpochDay(randomDay);
//    }
}
