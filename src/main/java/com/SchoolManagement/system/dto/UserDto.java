package com.SchoolManagement.system.dto;

import com.SchoolManagement.system.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String userName;
    private String email;
    private Integer age;
    private String gender;
    private String photoURL;
    private Set<Role> roles;
}
