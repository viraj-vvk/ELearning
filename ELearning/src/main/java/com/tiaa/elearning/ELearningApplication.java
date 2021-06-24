package com.tiaa.elearning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tiaa.elearning.models.Role;
import com.tiaa.elearning.repositories.RoleRepository;

@SpringBootApplication
public class ELearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {
		
		return args -> {
			
			Role role = roleRepository.findByRole("ADMIN");
			if(role == null) {
				Role newRole = new Role();
				newRole.setRole("ADMIN");
				roleRepository.save(newRole);
			}
			
			role = roleRepository.findByRole("TEACHER");
			if(role == null) {
				Role newRole = new Role();
				newRole.setRole("TEACHER");
				roleRepository.save(newRole);
			}
			
			role = roleRepository.findByRole("USER");
			if(role == null) {
				Role newRole = new Role();
				newRole.setRole("USER");
				roleRepository.save(newRole);
			}
		};
	}
}
