package com.hamzacode.demo.service.jwt;

import com.hamzacode.demo.model.Student;
import com.hamzacode.demo.repository.StudentRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepo studentRepo;

    public UserDetailsServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student std = this.studentRepo.getStudentByEmail(username);
        if(std == null) {
            throw new UsernameNotFoundException("Student Not Found", null);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        std.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        });

        return new User(std.getEmail(), new BCryptPasswordEncoder().encode(std.getPassword()), authorities);
    }
}
