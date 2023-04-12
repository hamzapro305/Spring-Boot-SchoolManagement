package com.hamzacode.demo.Student;

import com.hamzacode.demo.Exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getStudents")
    public List<Student> getStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/addStudent")
    public void addStudent(@RequestBody Student student){
        System.out.println(student);
    }

}
