package com.tiaa.elearning.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiaa.elearning.models.Content;
import com.tiaa.elearning.models.Course;

public interface ContentRepository extends MongoRepository<Content, String>{
	public List<Content> findByCourse(Course course);
}
