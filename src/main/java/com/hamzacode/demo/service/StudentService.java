package com.hamzacode.demo.service;

import com.hamzacode.demo.model.Student;
import com.hamzacode.demo.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public List<Student> getAllStudents(int Page, Integer Size){
        return this.studentRepo.selectAllStudents(Page, Size);
    }

    public boolean updatePhotoURL(String photoURL, UUID uid){
        return this.studentRepo.setPhotoURL(photoURL, uid);
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
