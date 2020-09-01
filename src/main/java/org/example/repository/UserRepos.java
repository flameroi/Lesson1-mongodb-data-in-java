package org.example.repository;

import org.example.docs.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepos extends MongoRepository <Users, Long> {
    Users findByEmpNo(String empNo);
    List<Users> findByFullNameLike(String fullName);
    @Query("fullName:'?O")
    List<Users> findCustomByFullName(String fullName);
}
