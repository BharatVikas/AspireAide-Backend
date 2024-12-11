package com.sms.Scholarship.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.Scholarship.dto.AdminLoginDto;
import com.sms.Scholarship.model.Admin;
import com.sms.Scholarship.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;  // Injecting the AdminService

    @PostMapping("/login")
    public Response adminLogin(@RequestBody AdminLoginDto adminLoginDto) {
        String message = adminService.adminLogin(adminLoginDto);
        boolean success = message.equals("Admin login successful");
        return new Response(success, message);
    }

    @GetMapping("/{id}")
    public Admin getAdminDetails(@PathVariable Long id) {
        return adminService.getAdminDetails(id);  // Calling on the instance of adminService
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

        public String getMessage() {
            return message;
        }
    }
}
