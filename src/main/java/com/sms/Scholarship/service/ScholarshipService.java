package com.sms.Scholarship.service;

import com.sms.Scholarship.model.Scholarship;
import com.sms.Scholarship.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    public Scholarship createScholarship(Scholarship scholarship) {
        return scholarshipRepository.save(scholarship);
    }

    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    public Optional<Scholarship> getScholarshipById(Long id) {
        return scholarshipRepository.findById(id);
    }

    public Scholarship updateScholarship(Long id, Scholarship newScholarshipDetails) {
        return scholarshipRepository.findById(id).map(existingScholarship -> {
            existingScholarship.setScholarshipName(newScholarshipDetails.getScholarshipName());
            existingScholarship.setDeadline(newScholarshipDetails.getDeadline());
            existingScholarship.setAmount(newScholarshipDetails.getAmount());
            existingScholarship.setCategory(newScholarshipDetails.getCategory());
            existingScholarship.setEligibility(newScholarshipDetails.getEligibility());
            existingScholarship.setDocuments(newScholarshipDetails.getDocuments());
            existingScholarship.setDescription(newScholarshipDetails.getDescription());
            existingScholarship.setIncome(newScholarshipDetails.getIncome());
            existingScholarship.setName(newScholarshipDetails.getName());
            existingScholarship.setEmail(newScholarshipDetails.getEmail());
            existingScholarship.setPhone(newScholarshipDetails.getPhone());
            return scholarshipRepository.save(existingScholarship);
        }).orElseThrow(() -> new IllegalArgumentException("Scholarship with ID " + id + " not found."));
    }

    public boolean deleteScholarship(Long id) {
        if (scholarshipRepository.existsById(id)) {
            scholarshipRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Scholarship> getScholarshipsByCategory(String category) {
        return scholarshipRepository.findByCategory(category);
    }

    public Scholarship updateScholarshipStatus(Long id, String status) {
        return scholarshipRepository.findById(id).map(scholarship -> {
            scholarship.setStatus(status);
            return scholarshipRepository.save(scholarship);
        }).orElseThrow(() -> new IllegalArgumentException("Scholarship with ID " + id + " not found."));
    }

    public List<Scholarship> getScholarshipsByStatus(String status) {
        return scholarshipRepository.findByStatus(status);
    }

    public List<Scholarship> getAllApplications() {
        return scholarshipRepository.findAll();
    }

    public Scholarship saveOrUpdateScholarship(Scholarship scholarship) {
        return scholarshipRepository.save(scholarship);
    }
    
    
    
}
