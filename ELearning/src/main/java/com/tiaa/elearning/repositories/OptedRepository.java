package com.tiaa.elearning.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiaa.elearning.models.Opted;

public interface OptedRepository extends MongoRepository<Opted, String>{
}
