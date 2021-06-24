package com.tiaa.elearning.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tiaa.elearning.configs.JwtTokenProvider;
import com.tiaa.elearning.models.Course;
import com.tiaa.elearning.models.User;
import com.tiaa.elearning.repositories.CourseRepository;
import com.tiaa.elearning.repositories.UserRepository;

@RestController
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository users;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@GetMapping("/api/courses")
	public Iterable<Course> course(){
		return courseRepository.findAll();
	}
	
	@PostMapping("/api/courses")
	public Course save(@RequestBody Course course,@RequestHeader (name="Authorization") String token) throws Exception {
		
		User user = users.findByEmail(jwtTokenProvider.getUsername(token));
		if(user==null) {
			throw new Exception("Invalid User Token");
		}
		user.setCreated_by(user);

		courseRepository.save(course);
		
		return course;
	}
	
	@GetMapping("/api/courses/{id}")
	public Optional<Course> show(@PathVariable String id){
		return courseRepository.findById(id);
	}
	
	@PutMapping("/api/courses/{id}")
	public Course update(@PathVariable String id, @RequestBody Course course) {
		Optional<Course> crs = courseRepository.findById(id);
		
		if(course.getCourseTitle() != null)
			crs.get().setCourseTitle(course.getCourseTitle());
		if(course.getCourseDesc() != null)
			crs.get().setCourseDesc(course.getCourseDesc());
		if(course.getCourseCoverImg() != null)
			crs.get().setCourseCoverImg(course.getCourseCoverImg());
		if(course.getCourseRating() != 0 )
			crs.get().setCourseRating(course.getCourseRating());
		if(course.getCourseSubscribers() != 0)
			crs.get().setCourseSubscribers(course.getCourseSubscribers());
		if(course.getFlag() != 0)
			crs.get().setFlag(course.getFlag());
		
		courseRepository.save(crs.get());
		return crs.get();
	}
	
	@DeleteMapping("/api/courses/{id}")
	public String delete(@PathVariable String id) {
		Optional<Course> course = courseRepository.findById(id);
		courseRepository.delete(course.get());
		
		return "Course Deleted";
	}
	
}
