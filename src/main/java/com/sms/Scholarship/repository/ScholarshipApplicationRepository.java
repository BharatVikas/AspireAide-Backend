package com.sms.Scholarship.repository;

import com.sms.Scholarship.model.ScholarshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScholarshipApplicationRepository extends JpaRepository<ScholarshipApplication, Long> {
    List<ScholarshipApplication> findBySubmissionDateBefore(LocalDate date);
    List<ScholarshipApplication> findByStatus(String status);
    List<ScholarshipApplication> findAll();
    List<ScholarshipApplication> findBySubmissionDateBefore(LocalDateTime dateTime);
    List<ScholarshipApplication> findByUserId(Long userId);
}	