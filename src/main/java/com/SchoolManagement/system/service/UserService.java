package com.SchoolManagement.system.service;

import com.SchoolManagement.system.model.User;
import com.SchoolManagement.system.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User save(User user) {
        return this.userRepo.insert(user);
    }

    public List<User> getAllUsers(){
        return this.userRepo.findAll();
    }

    public User getByName(String userName) {
        return this.userRepo.findByUserName(userName);
    }

    public User getByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

}
