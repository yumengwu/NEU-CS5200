package edu.northeastern.cs5200.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import edu.northeastern.cs5200.models.Enrollment;
import edu.northeastern.cs5200.repository.EnrollmentRepository;

@RestController
public class EnrollmentController {
  @Autowired
  EnrollmentRepository enrollmentRepository;

  @GetMapping("/api/enrollments")
  public List<Enrollment> getAllEnrollment() {
    return (List<Enrollment>) enrollmentRepository.findAll();
  }
}
