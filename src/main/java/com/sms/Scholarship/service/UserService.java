package com.sms.Scholarship.service;

import com.sms.Scholarship.dto.UserLoginDto;
import com.sms.Scholarship.dto.UserRegistrationDto;
import com.sms.Scholarship.model.User;
import com.sms.Scholarship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(UserRegistrationDto userRegistrationDto) {
        // Check if user already exists by email
        if (userRepository.findByEmail(userRegistrationDto.getEmail()) != null) {
            return "Email is already in use";
        }

        // Create a new User object
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setEmail(userRegistrationDto.getEmail());

        // Save the password without encoding
        user.setPassword(userRegistrationDto.getPassword());

        // Save the user to the database
        userRepository.save(user);
        return "Registration successful";
        
        
    }
    public String loginUser(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail());
        
        if (user == null) {
            return "User not found";
        }
        
        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            return "Invalid password";
        }
        
        return "Login successful";
    }
}
