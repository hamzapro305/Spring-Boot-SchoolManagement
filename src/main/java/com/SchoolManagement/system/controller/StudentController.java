package com.SchoolManagement.system.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/student")
public class StudentController {
    @GetMapping("/test")
    public String test() {
        return "Student Api Accessed";
    }
}
