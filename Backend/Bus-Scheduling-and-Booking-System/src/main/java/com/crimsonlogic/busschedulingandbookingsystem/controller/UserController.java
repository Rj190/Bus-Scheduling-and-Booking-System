package com.crimsonlogic.busschedulingandbookingsystem.controller;


import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import com.crimsonlogic.busschedulingandbookingsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
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
}

