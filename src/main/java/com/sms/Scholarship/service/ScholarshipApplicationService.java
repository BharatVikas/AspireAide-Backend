package com.sms.Scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sms.Scholarship.model.ScholarshipApplication;
import com.sms.Scholarship.repository.ScholarshipApplicationRepository;

import java.util.List;

@Service
public class ScholarshipApplicationService {

    @Autowired
    private ScholarshipApplicationRepository scholarshipApplicationRepository;

    // Method to save a scholarship application
    public ScholarshipApplication saveApplication(ScholarshipApplication application) {
        // Set the initial status of the application to "PENDING"
        application.setStatus("PENDING");
        return scholarshipApplicationRepository.save(application);
    }

    // Method to get all scholarship applications
    public List<ScholarshipApplication> getAllApplications() {
        return scholarshipApplicationRepository.findAll();
    }

    // Method to update the status of an application
    public ScholarshipApplication updateApplicationStatus(Long id, String status) {
        // Find the application by ID, throw an exception if not found
        ScholarshipApplication application = scholarshipApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with ID: " + id));
        
        // Update the status of the application
        application.setStatus(status);
        return scholarshipApplicationRepository.save(application);
    }
    
    public List<ScholarshipApplication> getPreviousApplications(Long userId) {
        return scholarshipApplicationRepository.findByUserId(userId);
    }
}