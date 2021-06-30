package com.tiaa.elearning.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tiaa.elearning.models.Course;
import com.tiaa.elearning.models.Opted;
import com.tiaa.elearning.models.User;
import com.tiaa.elearning.repositories.CourseRepository;
import com.tiaa.elearning.repositories.OptedRepository;
import com.tiaa.elearning.repositories.UserRepository;

@RestController
public class OptedController {

	@Autowired
	private OptedRepository optedRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@GetMapping("/api/opteds")
	public List<Opted> opteds(){
		return optedRepository.findAll();
	}
	
	@GetMapping("/api/opted/{oid}")
	public Optional<Opted> opted(@PathVariable String oid){
		return optedRepository.findById(oid);
	}
	
	@GetMapping("/api/opted/user/{id}")
	public List<Opted> userOpeds(@PathVariable String id){
		List<Opted> opteds = new ArrayList<>();
		
		optedRepository.findAll().forEach(opted->{
			if(opted.getUser().getId().equals(id))
				opteds.add(opted);
		});
		
		return opteds;
	}
	
	@GetMapping("/api/opted/course/{cid}")
	public List<Opted> courseOpteds(@PathVariable String cid){
		List<Opted> opteds = new ArrayList<>();
		
		optedRepository.findAll().forEach(opted->{
			if(opted.getCourse().getId().equals(cid))
				opteds.add(opted);
		});
		
		return opteds;
	}
	
	@PostMapping("/api/opted")
	public Opted save(@RequestBody Map<String,Object> body,@RequestHeader("Authorization")String token) throws Exception {

		String cid = (String)body.get("cid");
		User user = userRepository.findByEmail(jwtTokenProvider.getUsername(token.substring(7)));
		if(user==null) {
			throw new Exception("Invalid User Token");
		}
		
		Optional<Course> course = courseRepository.findById(cid);
		if(!course.isPresent()) {
			throw new Exception("Invalid Course");
		}

		Opted opted = new Opted();
		opted.setCourse(course.get());
		opted.setUser(user);
		
		return optedRepository.save(opted);
	}
	
	@PutMapping("/api/opted/{oid}")
	public Opted Update(@RequestBody Map<String,Object> body,@PathVariable String oid) throws Exception {

		float score = Float.parseFloat((String)body.get("score"));
		
		Opted opted = optedRepository.findById(oid).orElse(null);
		if(opted == null)
			throw new Exception("Wrong Request");
		
		opted.setScore(score);
		
		return optedRepository.save(opted);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/api/opted/{oid}")
	public ResponseEntity delete(@PathVariable String oid) {
		
		optedRepository.deleteById(oid);
		
		return ResponseEntity.ok("Data Deleted");
	}
	
}
