package com.SchoolManagement.system.controller;

import com.SchoolManagement.system.model.User;
import com.SchoolManagement.system.repository.UserRepo;
import com.SchoolManagement.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/basic-service/")
@AllArgsConstructor
public class BasicServiceController {

    private final UserService userService;
    private final UserRepo userRepo;

    @GetMapping()
    public String hello() {
        return "Hello, From Basic Services";
    }

    @GetMapping("getAllUsers")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("getCount")
    public ResponseEntity<?> getCount() {
        return ResponseEntity.ok(this.userRepo.getCount());
    }

    @GetMapping("getCountByGender")
    public ResponseEntity<?> getCountByGender() {
        return ResponseEntity.ok(this.userRepo.getCountByGender());
    }
}
