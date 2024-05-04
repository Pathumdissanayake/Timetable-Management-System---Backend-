package com.example.TMS.Repository;

import com.example.TMS.Entity.Timetable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimetableInterface extends MongoRepository<Timetable, String> {
}
