package com.crimsonlogic.busschedulingandbookingsystem.service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
    	user.setUserID(UUID.randomUUID().toString());
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String userID, User user) {
        // Check if the user exists
        getUserById(userID);

    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserID(userID); // Ensure the correct ID is set
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userID) {
        User user = getUserById(userID);
        userRepository.delete(user);
    }

    @Override
    public User getUserById(String userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
