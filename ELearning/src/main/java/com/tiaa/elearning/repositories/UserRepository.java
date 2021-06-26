package com.tiaa.elearning.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiaa.elearning.models.User;

public interface UserRepository extends MongoRepository<User, String>{
	public User findByEmail(String email);
}
