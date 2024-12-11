package com.sms.Scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.Scholarship.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // Optional: for checking if user already exists
}
