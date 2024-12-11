package com.sms.Scholarship.controller;

import com.sms.Scholarship.dto.UserLoginDto;
import com.sms.Scholarship.dto.UserRegistrationDto;
import com.sms.Scholarship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")  // Allow CORS for this controller
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        String message = userService.registerUser(userRegistrationDto);
        boolean success = message.equals("Registration successful"); // Corrected logic
        return new Response(success, message);
    }
    
    @PostMapping("/login")
    public Response loginUser(@RequestBody UserLoginDto userLoginDto) {
        String message = userService.loginUser(userLoginDto);
        boolean success = message.equals("Login successful");
        return new Response(success, message);
    }


    static class Response {
        private boolean success;
        private String message;

        public Response(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
