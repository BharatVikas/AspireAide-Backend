package com.sms.Scholarship.controller;

import com.sms.Scholarship.model.ScholarshipApplication;
import com.sms.Scholarship.repository.ScholarshipApplicationRepository;
import com.sms.Scholarship.service.ScholarshipApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
public class ScholarshipApplicationController {

    @Autowired
    private ScholarshipApplicationService scholarshipApplicationService;

    @Autowired
    private ScholarshipApplicationRepository scholarshipApplicationRepository;

    // Submit a scholarship application
    @PostMapping("/submit")
    public ResponseEntity<String> submitApplication(@RequestBody ScholarshipApplication application) {
        try {
            scholarshipApplicationService.saveApplication(application);
            return ResponseEntity.ok("Application submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error submitting application: " + e.getMessage());
        }
    }

    // Get all scholarship applications
    @GetMapping("/get-all-applications")
    public ResponseEntity<List<ScholarshipApplication>> getAllApplications() {
        List<ScholarshipApplication> applications = scholarshipApplicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    // Update application status to Accepted or Rejected (first method)
    @PutMapping("/update-status/{id}")
    public ResponseEntity<String> updateApplicationStatus(@PathVariable Long id, @RequestBody String status) {
        try {
            scholarshipApplicationService.updateApplicationStatus(id, status);
            return ResponseEntity.ok("Application status updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating application status: " + e.getMessage());
        }
    }

    // Get applications by user ID
    @GetMapping("/get-previous-applications")
    public ResponseEntity<List<ScholarshipApplication>> getApplicationsByUserId(@RequestParam Long userId) {
        List<ScholarshipApplication> applications = scholarshipApplicationRepository.findByUserId(userId);
        if (applications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(applications);
        }
        return ResponseEntity.ok(applications);
    }

    // Get all applications
    @GetMapping("/get-applications")
    public List<ScholarshipApplication> getApplications() {
        return scholarshipApplicationRepository.findAll();
    }

    // Update application status (second method with unique path)
    @PutMapping("/set-status-by-application/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody ScholarshipApplication updatedApplication) {
        return scholarshipApplicationRepository.findById(id).map(application -> {
            application.setStatus(updatedApplication.getStatus());
            scholarshipApplicationRepository.save(application);
            return ResponseEntity.ok("Application status updated successfully.");
        }).orElse(ResponseEntity.notFound().build());
    }
}
