package com.SchoolManagement.system.repository;

import com.SchoolManagement.system.dto.basicServiceDTO.GroupDTO;
import com.SchoolManagement.system.model.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, String> {


    User findByUserName(String userName);

    // Email is the main for JWT Token
    User findByEmail(String email);

    @Aggregation(pipeline = { "{$group: {_id: null, count: { $sum: 1 }}}" })
    List<?> getCount();

    @Aggregation(pipeline = { "{$group: {_id: '$gender', count: { $sum: 1 }}}" })
    List<GroupDTO> getCountByGender();

    @Aggregation(pipeline = { "{$group: {_id: '$type', count: { $sum: 1 }}}" })
    List<GroupDTO> getCountByType();

}
