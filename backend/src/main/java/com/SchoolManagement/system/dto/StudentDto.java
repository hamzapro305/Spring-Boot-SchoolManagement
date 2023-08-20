package com.SchoolManagement.system.dto;

import com.SchoolManagement.system.model.Role;
import com.SchoolManagement.system.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String userId;
    private String userName;
    private String fullName;
    private String email;
    private Integer age;
    private String gender;
    private String photoURL;
    private Set<Role> roles;
    private UserType type;
    private Map<String, Object> studentData;
}
