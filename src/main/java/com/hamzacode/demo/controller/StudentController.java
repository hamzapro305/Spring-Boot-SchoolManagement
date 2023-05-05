package com.hamzacode.demo.controller;

import com.hamzacode.demo.payloads.ApiResponse;
import com.hamzacode.demo.service.StudentService;
import com.hamzacode.demo.model.Student;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getStudents")
    public ResponseEntity<?> getStudents(@RequestParam(defaultValue = "0", required = true) int Page){

        Map<String, Object> response = new HashMap<>();

        response.put("allPages", this.studentService.getTotalPages());
        response.put("students", this.studentService.getAllStudents(Page));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/addStudent")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student std){
        if (std.getStudentId() != null) {
            return new ResponseEntity<>(
                    new ApiResponse("Cannot create a student with a specified ID", false),
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(this.studentService.addStudent(std), HttpStatus.OK);
    }

    @GetMapping("/getStudentById/{StudentId}")
    public ResponseEntity<?> getStudentById(@PathVariable UUID StudentId) {
        if(StudentId == null){
            return new ResponseEntity<>(
                    new ApiResponse("StudentId is null", false),
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(this.studentService.getStudentById(StudentId), HttpStatus.OK);
    }

}
