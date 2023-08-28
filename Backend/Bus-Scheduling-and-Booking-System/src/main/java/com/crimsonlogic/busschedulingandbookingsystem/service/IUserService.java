package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import com.crimsonlogic.busschedulingandbookingsystem.entity.User;

public interface IUserService {
	
	List<User> getAllUser();
	
    User createUser(User user);
    User updateUser(Integer userID, User user);
    void deleteUser(Integer userID);
    User getUserById(Integer userID);
    Optional<User> findByUsername(String username);

}
