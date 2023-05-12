package com.hamzacode.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    private UUID studentId;

    @NotEmpty(message = "User name is required")
    @Size(min = 6, message = "User name is less than 6 characters")
    private String userName;

    @NotEmpty(message = "Full name is required")
    @Size(min = 6, message = "Full name is less than 6 characters")
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

    @NotEmpty(message = "Password is blank")
    @Size(max = 50, message = "Maximum password length: 50 characters")
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    List<UserRole> roles = new ArrayList<>();

}
