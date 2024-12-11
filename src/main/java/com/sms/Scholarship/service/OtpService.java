package com.sms.Scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.sms.Scholarship.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, Integer> otpStorage = new HashMap<>();
    private Map<String, User> userStorage = new HashMap<>();
    private Map<String, Integer> attempts = new HashMap<>();
    private final int MAX_ATTEMPTS = 3;

    // Generate 6-digit OTP
    public int generateOtp() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // 6-digit OTP
    }

    // Send OTP to the user's email
    public void sendOtp(String email, int otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);

        // Store OTP and initialize/reset attempts for the user
        otpStorage.put(email, otp);
        attempts.put(email, 0); // Reset attempts whenever a new OTP is sent
    }

    // Validate OTP for a given email
    public boolean validateOtp(String email, int otp) {
        return otpStorage.containsKey(email) && otpStorage.get(email) == otp;
    }

    // Save user data temporarily for OTP verification
    public void saveUserForOtpVerification(User user) {
        userStorage.put(user.getEmail(), user);
    }

    // Retrieve user data based on email
    public User getUserForOtpVerification(String email) {
        return userStorage.get(email);
    }

    // Increment OTP verification attempts
    public void incrementAttempts(String email) {
        if (attempts.containsKey(email)) {
            attempts.put(email, attempts.get(email) + 1);
        }
    }

    // Get the number of attempts for a specific email
    public int getAttempts(String email) {
        return attempts.getOrDefault(email, 0);
    }

    // Reset attempts after successful OTP verification
    public void resetAttempts(String email) {
        attempts.put(email, 0);
    }

    // Check if user still has remaining attempts
    public boolean hasAttemptsLeft(String email) {
        return getAttempts(email) < MAX_ATTEMPTS;
    }
}
