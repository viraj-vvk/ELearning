package com.tiaa.elearning.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tiaa.elearning.models.Role;

public interface RoleRepository extends CrudRepository<Role, String>{
	Role findByRole(String role);
}
