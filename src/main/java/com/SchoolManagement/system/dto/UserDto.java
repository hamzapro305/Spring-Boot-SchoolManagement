package com.SchoolManagement.system.dto;

import com.SchoolManagement.system.model.Role;
import com.SchoolManagement.system.model.UserType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;

    @NotEmpty(message = "User name is required")
    @Size(min = 6, message = "User name is less than 6 characters")
    private String userName;

    private String fullName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 50, message = "email is too large")
    private String email;

    @NotNull(message = "Age is required")
    @PositiveOrZero(message = "Age cannot be negative")
    private Integer age;

    @NotEmpty(message = "Gender is required")
    @Pattern(regexp = "MALE|FEMALE", message = "Gender Values are {MALE, FEMALE} ")
    private String gender;

    private String photoURL;

    @NotNull(message = "Role is Required")
    private Set<Role> roles;

    private UserType type;

}
