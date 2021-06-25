package com.assessment.controller;

import java.util.ArrayList;
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
	
	@RequestMapping("/GenerateAssessment")
	public String create(String courseId, String question, @RequestParam(value="options") ArrayList<String> options,
			@RequestParam(value="answers") ArrayList<String> answers) {
		Assessment a = assessmentService.create(courseId, question, options, answers);
		return a.toString();
	}
	
	@RequestMapping("/UpdateQuestion")
	public String update(String courseId, String question, @RequestParam(value="options") ArrayList<String> options,
			@RequestParam(value="answers") ArrayList<String> answers) {
		Assessment a = assessmentService.update(courseId, question, options, answers);
		return a.toString();
	}
	
	@RequestMapping("/DeleteQuestion")
	public String delete(String question) {
		assessmentService.delete(question);
		return "Deleted Question : " + question;
	}
	
	@RequestMapping("/Assessment")
	public List<Assessment> getByCourseId(String courseId) {
		return assessmentService.getByCourseId(courseId);
	}
	
	@RequestMapping("/AllAssessments")
	public List<Assessment> getAssessments() {
		return assessmentService.getAll();
	}
	
}
