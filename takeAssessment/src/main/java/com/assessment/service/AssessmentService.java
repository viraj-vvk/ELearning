package com.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.model.Assessment;
import com.assessment.repository.AssessmentRepository;

@Service
public class AssessmentService {

	@Autowired
	private AssessmentRepository assessmentRepository;
	
	public List<Assessment> getAll(){
		return assessmentRepository.findAll();
	}
	public List<Assessment> getByCourseId(String courseId){
		return assessmentRepository.findByCourseId(courseId);
	}
}
