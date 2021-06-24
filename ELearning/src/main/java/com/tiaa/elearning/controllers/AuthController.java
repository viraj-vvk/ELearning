package com.tiaa.elearning.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiaa.elearning.configs.JwtTokenProvider;
import com.tiaa.elearning.models.User;
import com.tiaa.elearning.repositories.UserRepository;
import com.tiaa.elearning.services.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRepository users;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthBody data) {
		try {
			String username = data.getEmail();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, users.findByEmail(username).getRoles());
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ResponseEntity.ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("invalid credentials supplied");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("register/{key}")
	public ResponseEntity register(@PathVariable int key, @RequestBody User user,@RequestHeader (name="Authorization") String token) {
		
		User userExists = userService.findUserByEmail(user.getEmail());
		if(userExists != null) {
			throw new BadCredentialsException("User with username: "+user.getEmail()+" already exists");
		}
		
		userService.saveUser(userExists,key);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User Registerd Successfully");
		
		return ResponseEntity.ok(model);
	}
}
