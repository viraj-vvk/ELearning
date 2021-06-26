package com.tiaa.elearning.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tiaa.elearning.models.Course;

public interface CourseRepository extends CrudRepository<Course, String>{
	@Override
	public void delete(Course deleted);
}
