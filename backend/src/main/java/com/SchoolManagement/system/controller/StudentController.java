package com.SchoolManagement.system.controller;


import com.SchoolManagement.system.model.User;
import com.SchoolManagement.system.service.jwt.JwtService;
import com.SchoolManagement.system.service.jwt.JwtUserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/api/student")
@AllArgsConstructor
public class StudentController {
    private final JwtService jwtService;
    private final JwtUserDetailsServiceImpl jwtUserDetailsService;

    @GetMapping("/getStudentData")
    public ResponseEntity<?> getStudentData(HttpServletRequest request) {
        String token = this.jwtService.extractTokenFromRequest(request);
        String userName = this.jwtService.getUserNameFromJwtToken(token);
        User user = this.jwtUserDetailsService.loadUserByUsername(userName);
        Map<String, Object> resp = new HashMap<>();
        resp.put("User", user);
        return ResponseEntity.ok(resp);

    }
}
