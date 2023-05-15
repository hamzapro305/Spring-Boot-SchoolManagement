package com.hamzacode.demo.dto;

import com.hamzacode.demo.model.UserRole;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class StudentResponseDTO {
    private UUID studentId;
    private String photoURL;
    private String userName;
    private String fullName;
    private String email;
    private Integer age;
    private String gender;
    private List<UserRole> roles;

    @Override
    public String toString() {
        return "StudentResponseDTO{" +
                "studentId=" + studentId +
                ", photoURL='" + photoURL + '\'' +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", roles=" + roles +
                '}';
    }
}
