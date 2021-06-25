package com.assessment.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("questions")
public class Assessment {

	@Id
	String id;
	String courseId;
	String question;
	ArrayList<String> options = new ArrayList<String>();
	ArrayList<String> answer = new ArrayList<String>();
	public Assessment(String id, String courseId, String question, ArrayList<String> options,
			ArrayList<String> answer) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.question = question;
		this.options = options;
		this.answer = answer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<String> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
	public ArrayList<String> getAnswer() {
		return answer;
	}
	public void setAnswer(ArrayList<String> answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		System.out.println("hey");
		return "Assessment [id=" + id + ", courseId=" + courseId + ", question=" + question + ", options=" + options
				+ ", answer=" + answer + "]";
	}
	
	
}
