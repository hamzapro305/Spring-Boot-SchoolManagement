package com.SchoolManagement.system.controller;

import com.SchoolManagement.system.dto.UserDto;
import com.SchoolManagement.system.dto.request.LoginRequest;
import com.SchoolManagement.system.dto.request.SignupRequest;
import com.SchoolManagement.system.model.User;
import com.SchoolManagement.system.service.UserService;
import com.SchoolManagement.system.service.jwt.JwtService;
import com.SchoolManagement.system.service.jwt.JwtUserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final JwtUserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        Authentication auth = this.doAuthenticate(user, loginRequest.getPassword());
        String token = this.jwtService.generateJwtToken(auth);
        Map<String, Object> resp = new HashMap<>();
        resp.put("token", token);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        ModelMapper m = new ModelMapper();
        User user = m.map(signupRequest, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        UserDto resp = m.map(userService.save(user), UserDto.class);
        return ResponseEntity.ok(resp);
    }

    private Authentication doAuthenticate(UserDetails user, String password) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(), password, user.getAuthorities()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

}
