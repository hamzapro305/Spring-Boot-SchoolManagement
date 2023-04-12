package com.hamzacode.demo.Student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents(){
        return studentRepo.selectAllStudents();
    }
}
