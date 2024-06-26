package com.demo.resource;

import com.demo.dto.request.CreateUserRequest;
import com.demo.dto.utils.ResponseUtils;
import com.demo.entity.Team;
import com.demo.entity.User;
import com.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams")
@CrossOrigin("*")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<?> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return ResponseUtils.ok(teams);
    }


}
