package com.sms.Scholarship.service;

import com.sms.Scholarship.dto.AdminLoginDto;
import com.sms.Scholarship.model.Admin;
import com.sms.Scholarship.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public String adminLogin(AdminLoginDto adminLoginDto) {
        Admin admin = adminRepository.findByEmail(adminLoginDto.getEmail());

        if (admin == null) {
            return "Admin not found";
        }

        if (!admin.getPassword().equals(adminLoginDto.getPassword())) {
            return "Invalid password";
        }

        return "Admin login successful";
    }

    public Admin getAdminDetails(Long id) {
        return adminRepository.findById(id).orElse(null);  // Fetch admin by ID
    }
}
