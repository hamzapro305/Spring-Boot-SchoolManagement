package com.SchoolManagement.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basic-service/")
public class BasicServiceController {
    @GetMapping()
    public String hello() {
        return "Hello, From Basic Services";
    }
}
