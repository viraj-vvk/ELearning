package com.tiaa.elearning.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "opteds")
public class Opted {
	
	@Id
	private String id;
	
	@DBRef
	private Course course;
	
	@DBRef
	private User user;
	
	private float score;
	private Date opted_on;
	private Date completed_on;
	
	public Opted() {
		opted_on = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOpted_on() {
		return opted_on;
	}

	public void setOpted_on(Date opted_on) {
		this.opted_on = opted_on;
	}

	public Date getCompleted_on() {
		return completed_on;
	}

	public void setCompleted_on(Date completed_on) {
		this.completed_on = completed_on;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
