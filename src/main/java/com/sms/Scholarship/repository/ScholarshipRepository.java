package com.sms.Scholarship.repository;

import com.sms.Scholarship.model.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    List<Scholarship> findByStatus(String status);
    List<Scholarship> findByCategory(String category);
    List<Scholarship> findAll();
    List<Scholarship> findByNameAndEmail(String name, String email);
	
	}
