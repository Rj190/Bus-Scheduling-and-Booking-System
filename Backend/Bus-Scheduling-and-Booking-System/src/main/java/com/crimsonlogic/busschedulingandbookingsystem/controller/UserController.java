package com.crimsonlogic.busschedulingandbookingsystem.controller;


import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user-by/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            Optional<User> existingUser = userService.findByUsername(user.getUsername());
            if (existingUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username Taken");
            } else {
                User createdUser = userService.createUser(user);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the user.");
        }
    }


    @PutMapping("/update-user-by/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete-user-by/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal) {
    	return principal.getName();
    }
    
    @GetMapping("/user-by/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username).orElseThrow(() ->  new ResourceNotFoundException("User", "UserName", username));
        return ResponseEntity.ok(user);
    }
    
    
	/**
	 * Handle RouteNotFoundException and return appropriate response.
	 * 
	 * @param ex RouteNotFoundException instance
	 * @return ResponseEntity with NOT_FOUND status and error message
	 */
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
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	}
}

