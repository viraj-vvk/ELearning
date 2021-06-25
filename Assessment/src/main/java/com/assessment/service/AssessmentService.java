package com.assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.model.Assessment;
import com.assessment.repository.AssessmentRepository;

@Service
public class AssessmentService {

	@Autowired
	private AssessmentRepository assessmentRepository;
	
	public Assessment create(String courseId, String question, ArrayList<String> options,
			ArrayList<String> answers)
	{
		return assessmentRepository.save(new Assessment(courseId, question, options, answers));
	}
	
	public Assessment update(String courseId, String question, ArrayList<String> options,
			ArrayList<String> answers)
	{
		Assessment a = assessmentRepository.findByQuestion(question);
		a.setCourseId(courseId);
		a.setQuestion(question);
		a.setOptions(options);
		a.setAnswer(answers);
		return assessmentRepository.save(a);
		
	}
	
	public void delete(String question)
	{
		Assessment a = assessmentRepository.findByQuestion(question);
		assessmentRepository.delete(a);
		
	}
	
	public List<Assessment> getAll(){
		return assessmentRepository.findAll();
	}
	public List<Assessment> getByCourseId(String courseId){
		return assessmentRepository.findByCourseId(courseId);
	}
}
