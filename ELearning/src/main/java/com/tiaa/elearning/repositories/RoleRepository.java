package com.tiaa.elearning.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiaa.elearning.models.Role;

public interface RoleRepository extends MongoRepository<Role, String>{
	public Role findByRole(String role);
}
