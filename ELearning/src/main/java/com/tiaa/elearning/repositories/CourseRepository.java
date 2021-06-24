package com.tiaa.elearning.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tiaa.elearning.models.Course;

public interface CourseRepository extends CrudRepository<Course, String>{
	@Override
	void delete(Course entity);
}