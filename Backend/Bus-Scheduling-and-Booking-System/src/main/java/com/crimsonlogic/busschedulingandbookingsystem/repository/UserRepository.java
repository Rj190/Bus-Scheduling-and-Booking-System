package com.crimsonlogic.busschedulingandbookingsystem.repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
