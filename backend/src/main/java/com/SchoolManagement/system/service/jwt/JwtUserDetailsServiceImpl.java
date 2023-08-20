package com.SchoolManagement.system.service.jwt;

import com.SchoolManagement.system.model.User;
import com.SchoolManagement.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User Not Found!");
        return user;
    }
}
