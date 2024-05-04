package com.example.TMS.Repository;

import com.example.TMS.Entity.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourceInterface extends MongoRepository<Resources, String> {
}
