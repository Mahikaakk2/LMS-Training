package com.lms.training.service;

import com.lms.training.Dto.EnrollmentsDto;

import java.util.List;

public interface EnrollmentService {
    public void createAccount(EnrollmentsDto enrollmentsDto);
    public List<EnrollmentsDto> fetchAllAccountDetails();
}
