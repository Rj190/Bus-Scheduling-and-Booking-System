package com.crimsonlogic.busschedulingandbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private IUserService  userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
			return user;
	}
	
	
	

}
