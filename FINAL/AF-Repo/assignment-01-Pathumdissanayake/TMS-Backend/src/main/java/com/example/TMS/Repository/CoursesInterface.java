package com.example.TMS.Repository;

import com.example.TMS.Entity.Courses;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoursesInterface extends MongoRepository<Courses, String> {
}
