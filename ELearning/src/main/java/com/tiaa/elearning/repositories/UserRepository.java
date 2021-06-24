package com.tiaa.elearning.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tiaa.elearning.models.User;

public interface UserRepository extends CrudRepository<User, String>{
	User findByEmail(String email);
}
