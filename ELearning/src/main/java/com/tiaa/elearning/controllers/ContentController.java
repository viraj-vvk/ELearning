package com.tiaa.elearning.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tiaa.elearning.configs.JwtTokenProvider;
import com.tiaa.elearning.models.Content;
import com.tiaa.elearning.models.Course;
import com.tiaa.elearning.repositories.ContentRepository;
import com.tiaa.elearning.repositories.CourseRepository;

@RestController
public class ContentController {

	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@GetMapping("/api/contents/{id}")
	public List<Content> contetnts(@PathVariable String id){
//		List<Content> contents = new ArrayList<>();
//		
//		contentRepository.findAll().forEach(content -> {
//			if(content.getCourse().getId() == id)
//				contents.add(content);
//		});
//		
//		return contents;
		
		Optional<Course> course = courseRepository.findById(id);
		if(!course.isPresent())
			return null;
		return contentRepository.findByCourse(course.get());
	}
	
	@GetMapping("/api/content/{cid}")
	public Optional<Content> content(@PathVariable String cid) {
		return contentRepository.findById(cid);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/api/content/{id}")
	public ResponseEntity save(@PathVariable String id,@RequestBody Content content,@RequestHeader("Authorization") String token)throws Exception{
		
		Optional<Course> course = courseRepository.findById(id);
		if(!course.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		
		String email = jwtTokenProvider.getUsername(token.substring(7));
		if(!course.get().getCourseAddedBy().getEmail().equals(email)) 
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		
		content.setCourse(course.get());
		contentRepository.save(content);
		
		return ResponseEntity.ok(content);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/api/content/{cid}")
	public ResponseEntity update(@PathVariable String cid,@RequestBody Content content,@RequestHeader("Authorization") String token) {
		Content cnt = contentRepository.findById(cid).orElse(null);
		
		if(cnt==null)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		
		String email = jwtTokenProvider.getUsername(token.substring(7));
		if(!cnt.getCourse().getCourseAddedBy().getEmail().equals(email))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		
		if(content.getTitle()!=null)
			cnt.setTitle(content.getTitle());
		if(content.getDesc()!=null)
			cnt.setDesc(content.getDesc());
		if(content.getNumber()!=0)
			cnt.setNumber(content.getNumber());
		if(content.getPath()!=null)
			cnt.setPath(content.getPath());
		cnt.setUpdatedOn(new Date());
		
		return ResponseEntity.ok(contentRepository.save(cnt));
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/api/content/{cid}")
	public ResponseEntity delete(@PathVariable String cid,@RequestHeader("Authorization") String token) {
		Content content = contentRepository.findById(cid).orElse(null);
		
		if(content==null)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		
		String email = jwtTokenProvider.getUsername(token.substring(7));
		if(!content.getCourse().getCourseAddedBy().getEmail().equals(email))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		
		contentRepository.delete(content);
		return ResponseEntity.ok("Content Deleted");
	}
	
}
