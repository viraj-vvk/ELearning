package com.tiaa.elearning.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiaa.elearning.models.Role;
import com.tiaa.elearning.models.User;
import com.tiaa.elearning.repositories.RoleRepository;
import com.tiaa.elearning.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void saveUser(User user,int type) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Role userRole;
		
		switch (type) {
		case 1:
			userRole= roleRepository.findByRole("ADMIN");			
			break;
		case 2:
			userRole= roleRepository.findByRole("TEACHER");
			break;
		default:
			userRole= roleRepository.findByRole("USER");
		}
		
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		
		if(user!=null) {
			List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
			return buildUserForAuthentication(user,authorities);
		}
		else {
			throw new UsernameNotFoundException("username not found");
		}
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		userRoles.forEach((role)->{
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		});
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user,List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}
	
}