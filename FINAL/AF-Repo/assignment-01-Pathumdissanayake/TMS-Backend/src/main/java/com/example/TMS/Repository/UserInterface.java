package com.example.TMS.Repository;

import com.example.TMS.Entity.Courses;
import com.example.TMS.Entity.Role;
import com.example.TMS.Entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserInterface extends MongoRepository<Users, String> {
    Optional<Users> findByEmail(String email);
    Users findByRole(Role role);
    List<Users> findByStudentIdIn(List<String> studentIds);
    Optional<Users> findByStudentId(String studentId);
}
