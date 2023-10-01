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

    @GetMapping("/getStudentData")
    public ResponseEntity<?> getStudentData(HttpServletRequest request) {
        User user = this.jwtService.getUserFromJwtTokenRequest(request);
        Map<String, Object> resp = new HashMap<>();
        resp.put("User", user);

        return ResponseEntity.ok(resp);
    }
}
