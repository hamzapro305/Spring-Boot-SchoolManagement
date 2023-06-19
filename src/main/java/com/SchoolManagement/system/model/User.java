package com.SchoolManagement.system.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String userId;

    @NotEmpty(message = "User name is required")
    @Size(min = 6, message = "User name is less than 6 characters")
    @Indexed(unique = true)
    private String userName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 50, message = "email is too large")
    @Indexed(unique = true, sparse = true)
    private String email;

    @NotEmpty(message = "Password is blank")
    @Size(max = 50, message = "Maximum password length: 50 characters")
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @NotNull(message = "Age is required")
    @PositiveOrZero(message = "Age cannot be negative")
    private Integer age;

    @NotEmpty(message = "Gender is required")
    @Pattern(regexp = "MALE|FEMALE", message = "Gender Values are {MALE, FEMALE} ")
    private String gender;

    private String photoURL;

    @NotNull(message = "Role is Required")
    private UserRole role;

}
