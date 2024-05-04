package com.example.TMS.Repository;

import com.example.TMS.Entity.Enrollment;
import com.example.TMS.Entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EnrollementInterface extends MongoRepository<Enrollment,String> {
    List<Enrollment> findByCourseId(String courseId);
}
