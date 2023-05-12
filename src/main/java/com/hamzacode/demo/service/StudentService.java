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

    public List<Student> getAllStudents(int Page, Integer Size){
        return this.studentRepo.selectAllStudents(Page, Size);
    }

    public Student addStudent(Student std) {
        return this.studentRepo.addStudent(std);
    }
    public Student getStudentById(UUID id){
        return this.studentRepo.getStudentById(id);
    }

    public Student getStudentByEmail(String Email){
        return this.studentRepo.getStudentByEmail(Email);
    }

    public Integer getTotalPages(Integer Size) { return this.studentRepo.getTotalPages(Size); }

    public void deleteStudent(UUID uid) {
        this.studentRepo.deleteStudent(uid);
    }
}
