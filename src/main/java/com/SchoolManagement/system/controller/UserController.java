package com.SchoolManagement.system.controller;

import com.SchoolManagement.system.dto.UserDto;
import com.SchoolManagement.system.model.User;
import com.SchoolManagement.system.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUser")
    public UserDto getUserByUserName(@RequestParam("userName") String userName){
        User user =  this.userService.getByName(userName);
        ModelMapper m = new ModelMapper();
        return m.map(user, UserDto.class);
    }
}