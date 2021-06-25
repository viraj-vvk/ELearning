package com.assessment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assessment.model.Assessment;

@Repository
public interface AssessmentRepository extends MongoRepository<Assessment, String>{

	public List<Assessment>  findByCourseId(String courseId);
	public Assessment findByQuestion(String question);

}
