package com.cts.ResultApp.repo;

import com.cts.ResultApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Primary method for fetching user details during Spring Security login
    Optional<User> findByUsername(String username);

    // Added: Find user by email (useful for user service logic)
    Optional<User> findByEmailId(String emailId); // <--- ADDED THIS LINE

    // Check if an email already exists (Existing method)
    Boolean existsByEmailId(String emailId);

    // Added: Check if a username already exists (useful for registration validation)
    Boolean existsByUsername(String username); // <--- ADDED THIS LINE
}

