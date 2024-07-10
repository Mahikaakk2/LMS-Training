package com.lms.training.service.impl;

import com.lms.training.Dto.EnrollmentsDto;
import com.lms.training.entity.Enrollments;
import com.lms.training.mapper.EnrollmentsMapper;
import com.lms.training.repository.EnrollmentRepository;
import com.lms.training.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Override
    public void createAccount(EnrollmentsDto enrollmentsDto) {
//        Optional<Enrollments> foundEnrollment = EnrollmentRepository.findByNewJoinId(enrollmentsDto.getNewJoinID());
//
//        if(foundEnrollment.isPresent()){
//            throw new EnrollmentAlreadyExistsException("Enrollment already exists for this id - " + enrollmentsDto.getNewJoinID());
//        }
//
       Enrollments enrollments = EnrollmentsMapper.mapToEnrollments(enrollmentsDto, new Enrollments());
       enrollments.setCreatedAt(LocalDateTime.now());
       enrollments.setCreatedBy("Anonymous");
       enrollmentRepository.save(enrollments);

    }

    @Override
    public List<EnrollmentsDto> fetchAllAccountDetails() {
        List<Enrollments> enrollments= enrollmentRepository.findAll();
        //change to dtos
        List<EnrollmentsDto> enrollmentsDtos= new ArrayList<>();
        for(Enrollments e:enrollments)
        {
            EnrollmentsDto enroll = EnrollmentsMapper.mapToEnrollmentsDto(e, new EnrollmentsDto());
            enrollmentsDtos.add(enroll);
        }
       return enrollmentsDtos;
    }
}
