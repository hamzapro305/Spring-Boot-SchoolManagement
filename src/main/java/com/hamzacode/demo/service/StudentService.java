package com.hamzacode.demo.service;

import com.hamzacode.demo.model.Student;
import com.hamzacode.demo.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    public final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents(int Page){
        return this.studentRepo.selectAllStudents(Page);
    }

    public Student addStudent(Student std) {
        return this.studentRepo.addStudent(std);
    }
    public Student getStudentById(UUID id){
        return this.studentRepo.getStudentById(id);
    }

    public Integer getTotalPages() { return this.studentRepo.getTotalPages(); }
}
