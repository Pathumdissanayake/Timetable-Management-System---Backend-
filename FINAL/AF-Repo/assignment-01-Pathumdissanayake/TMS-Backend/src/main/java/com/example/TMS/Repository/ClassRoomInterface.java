package com.example.TMS.Repository;

import com.example.TMS.Entity.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClassRoomInterface extends MongoRepository<Classroom, String> {
    Optional<Classroom> findByRoomId(String roomId);
}
