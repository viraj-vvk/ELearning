package com.tiaa.elearning.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {

	@Id
	private String Id;
	
	@DBRef
	private User courseAddedBy;
	
	private String courseTitle;
	private String courseCoverImg;
	private String courseDesc;
	private int courseRating=0;
	private int courseSubscribers=0;
	private String courseCreatedOn;
	private String courseUpdatedOn;
	private byte flag;
	
	public Course() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public User getCourseAddedBy() {
		return courseAddedBy;
	}

	public void setCourseAddedBy(User courseAddedBy) {
		this.courseAddedBy = courseAddedBy;
	}

	public String getCourseCoverImg() {
		return courseCoverImg;
	}

	public void setCourseCoverImg(String courseCoverImg) {
		this.courseCoverImg = courseCoverImg;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public int getCourseRating() {
		return courseRating;
	}

	public void setCourseRating(int courseRating) {
		this.courseRating = courseRating;
	}

	public int getCourseSubscribers() {
		return courseSubscribers;
	}

	public void setCourseSubscribers(int courseSubscribers) {
		this.courseSubscribers = courseSubscribers;
	}

	public String getCourseCreatedOn() {
		return courseCreatedOn;
	}

	public void setCourseCreatedOn(String courseCreatedOn) {
		this.courseCreatedOn = courseCreatedOn;
	}

	public String getCourseUpdatedOn() {
		return courseUpdatedOn;
	}

	public void setCourseUpdatedOn(String courseUpdatedOn) {
		this.courseUpdatedOn = courseUpdatedOn;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	
}
