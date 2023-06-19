package com.SchoolManagement.system.controller;

import com.SchoolManagement.system.model.User;
import com.SchoolManagement.system.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody @Valid User user) {
        return userService.save(user);
    }
    @GetMapping("/getUser")
    public User getUserByUserName(@RequestParam("userName") String userName){
        return this.userService.getByName(userName);
    }
    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }
}