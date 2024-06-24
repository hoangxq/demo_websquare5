package com.demo.resource;

import com.demo.dto.request.RemoveUsersReq;
import com.demo.dto.request.UserCriteria;
import com.demo.dto.utils.PagingReq;
import com.demo.dto.utils.ResponseUtils;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserResource {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> getUsers(@RequestBody(required = false) UserCriteria userCriteria, @Validated PagingReq pagingReq) {
        return ResponseUtils.ok(userService.getUsers(userCriteria, pagingReq));
    }

    @PostMapping("/upsert")
    public ResponseEntity<?> upsertUser(@RequestBody User user) {
        userService.upsertUser(user);
        return ResponseUtils.ok();
    }

    @DeleteMapping("/remove-users")
    public ResponseEntity<?> removeUsers(@RequestBody RemoveUsersReq removeUsersReq) {
        userService.removeUsers(removeUsersReq);
        return ResponseUtils.ok();
    }

    @PostMapping("/export-excel")
    public ResponseEntity<?> exportDataUsers(@RequestBody(required = false) UserCriteria userCriteria) {
        return ResponseUtils.ok(userService.exportDataUsers(userCriteria));
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
