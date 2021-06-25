package com.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.model.Assessment;
import com.assessment.service.AssessmentService;

@CrossOrigin
@RestController
public class AssessmentController {

	@Autowired
	private AssessmentService assessmentService;
	
	@RequestMapping("/Assessment")
	public List<Assessment> getByCourseId(String courseId) {
		return assessmentService.getByCourseId(courseId);
	}
	
	@RequestMapping("/AllAssessments")
	public List<Assessment> getAssessments() {
		return assessmentService.getAll();
	}
	
}
