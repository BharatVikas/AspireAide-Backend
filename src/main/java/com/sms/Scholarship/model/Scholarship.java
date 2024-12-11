package com.sms.Scholarship.model;

import jakarta.persistence.*;

@Entity
public class Scholarship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scholarshipName;
    private String deadline;
    private String amount;
    private String category;
    private String eligibility;
    private String documents;
    private String description;
    private String status;
    private int income;
    private String name;  // New field for student name
    private String email; // New field for student email
    private String phone; // New field for student phone

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getName() { // Getter for name
        return name;
    }

    public void setName(String name) { // Setter for name
        this.name = name;
    }

    public String getEmail() { // Getter for email
        return email;
    }

    public void setEmail(String email) { // Setter for email
        this.email = email;
    }

    public String getPhone() { // Getter for phone
        return phone;
    }

    public void setPhone(String phone) { // Setter for phone
        this.phone = phone;
    }
}
