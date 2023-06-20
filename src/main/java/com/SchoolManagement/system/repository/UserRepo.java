package com.SchoolManagement.system.repository;

import com.SchoolManagement.system.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    User findByUserName(String userName);
    User findByEmail(String email);
}
