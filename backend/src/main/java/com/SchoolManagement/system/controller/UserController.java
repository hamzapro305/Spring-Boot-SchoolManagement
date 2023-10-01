package com.SchoolManagement.system.controller;

import com.SchoolManagement.system.dto.UserDto;
import com.SchoolManagement.system.model.User;
import com.SchoolManagement.system.service.UserService;
import com.SchoolManagement.system.service.jwt.JwtService;
import com.SchoolManagement.system.service.jwt.JwtUserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private JwtService jwtService;

    @GetMapping("/getUser")
    public UserDto getUserByUserName(HttpServletRequest request){
        User user =  this.jwtService.getUserFromJwtTokenRequest(request);
        ModelMapper m = new ModelMapper();
        return m.map(user, UserDto.class);
    }

    @PutMapping("/updateUser")
    public UserDto updateUser() {
        
        return null;
    }

}