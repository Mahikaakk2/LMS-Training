package com.lms.training.service;

import com.lms.training.Dto.EnrollmentsDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EnrollmentService {
    public void createEnrollment(EnrollmentsDto enrollmentsDto);
    public List<EnrollmentsDto> fetchAllEnrollmentDetails();
    public List<EnrollmentsDto> fetchEnrollmentsByCourseID(@RequestParam int courseID);
    public List<EnrollmentsDto> fetchEnrollmentsByMentorID(@RequestParam int courseID);
    public List<EnrollmentsDto> fetchEnrollmentsByNewJoinID(@RequestParam int newJoinID);
}
