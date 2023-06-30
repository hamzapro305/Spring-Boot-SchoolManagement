package com.SchoolManagement.system.dto.request;

import com.SchoolManagement.system.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {

    @NotEmpty(message = "User name is required")
    @Size(min = 6, message = "User name is less than 6 characters")
    @Indexed(unique = true)
    private String userName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 50, message = "email is too large")
    private String email;

    @NotEmpty(message = "Password is blank")
    @Size(max = 50, message = "Maximum password length: 50 characters")
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @NotEmpty(message = "No Roles Provided")
    private Set<Role> roles;

}
