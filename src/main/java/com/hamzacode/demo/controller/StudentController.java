package com.hamzacode.demo.controller;

import com.hamzacode.demo.dto.StudentResponseDTO;
import com.hamzacode.demo.model.UserRole;
import com.hamzacode.demo.payloads.*;
import com.hamzacode.demo.service.StudentService;
import com.hamzacode.demo.model.Student;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/getStudents")
    public ResponseEntity<?> getStudents(
            @RequestParam(defaultValue = "0", required = true) int Page,
            @RequestParam(defaultValue = "2", required = true) int Size
        ){

        if(Page < 0) {
            ApiErrorResponse resp = new ApiErrorResponse("Page no cannot be less than zero");
            return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
        }
        if(Size < 1) {
            ApiErrorResponse resp = new ApiErrorResponse("Size cannot be less than 1");
            return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
        }

        Map<String, Object> Data = new HashMap<>();

        Data.put("totalPages", this.studentService.getTotalPages(Size));
        List<StudentResponseDTO> allStudents = new ArrayList<>();
        for(Student std: this.studentService.getAllStudents(Page, Size)) {
            allStudents.add(
                    modelMapper.map(std, StudentResponseDTO.class)
            );
        }
        Data.put("students", allStudents);

        ApiResponse resp = new ApiResponse(Data);

        return resp.getResponse(HttpStatus.OK);
    }

    @GetMapping("/getStudentById/{StudentId}")
    public ResponseEntity<?> getStudentById(@PathVariable UUID StudentId) {
        if(StudentId == null){
            ApiErrorResponse resp = new ApiErrorResponse("StudentId is null");
            return resp.getResponse(HttpStatus.NOT_ACCEPTABLE);
        }
        Student stdData = this.studentService.getStudentById(StudentId);
        if(stdData == null){
            ApiErrorResponse resp = new ApiErrorResponse("Student Not Found");
            return resp.getResponse(HttpStatus.NOT_FOUND);
        }
        final StudentResponseDTO std = modelMapper.map(stdData, StudentResponseDTO.class);
        ApiResponse resp = new ApiResponse(std);
        return resp.getResponse(HttpStatus.OK);
    }

    @PostMapping(value = "/updatePhotoURL")
    public ResponseEntity<?> updatePhoto(@Valid @RequestBody UpdateImagePayload body){
        boolean status = this.studentService.updatePhotoURL(body.getPhotoURL(), body.getUid());
        if(status){
            ApiResponse resp = new ApiResponse("Photo url updated");
            return resp.getResponse(HttpStatus.OK);
        }else {
            ApiErrorResponse resp = new ApiErrorResponse("Server Error");
            return resp.getResponse(HttpStatus.OK);
        }
    }

    @PostMapping(value = "/addStudent")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student std){
        if (std.getStudentId() != null) {
            ApiErrorResponse resp = new ApiErrorResponse("Cannot create a student with a specified ID");
            return resp.getResponse(HttpStatus.BAD_REQUEST);
        }
        std.getRoles().add(UserRole.ROLE_STUDENT);
        ApiResponse resp = new ApiResponse(this.studentService.addStudent(std));
        return resp.getResponse(HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteStudent")
    public ResponseEntity<?> deleteStudent(@RequestParam(required = true) UUID uid){
        this.studentService.deleteStudent(uid);
        ApiResponse resp = new ApiResponse("Student Deleted");
        return resp.getResponse(HttpStatus.OK);
    }

}
