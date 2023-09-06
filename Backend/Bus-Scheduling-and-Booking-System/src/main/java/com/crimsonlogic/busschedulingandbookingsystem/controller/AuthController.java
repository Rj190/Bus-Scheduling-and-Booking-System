package com.crimsonlogic.busschedulingandbookingsystem.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.busschedulingandbookingsystem.entity.JwtRequest;
import com.crimsonlogic.busschedulingandbookingsystem.entity.JwtResponse;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.security.JwtHelper;


@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
    	 logger.info("Attempting authentication for user: {}",request );
    	 
    	try {  this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    		
    	}catch (Exception e) {
    	    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

      
    }

    private void doAuthenticate(String userName, String password) {
    	 logger.info("Attempting authentication for user: {}", userName);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
        	e.printStackTrace();
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    
	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<String> handleRouteNotFoundException(RouteNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	/**
	 * Handle other exceptions and return INTERNAL_SERVER_ERROR status.
	 * 
	 * @param ex Exception instance
	 * @return ResponseEntity with INTERNAL_SERVER_ERROR status and error message
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	
	}
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
