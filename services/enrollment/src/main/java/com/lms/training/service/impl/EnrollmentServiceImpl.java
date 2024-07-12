package com.lms.training.service.impl;

import com.lms.training.Dto.EnrollmentsDto;
import com.lms.training.entity.Enrollments;
import com.lms.training.exception.ResourceNotFoundException;
import com.lms.training.mapper.EnrollmentsMapper;
import com.lms.training.repository.EnrollmentRepository;
import com.lms.training.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Override
    public void createEnrollment(EnrollmentsDto enrollmentsDto) {
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
    public List<EnrollmentsDto> fetchAllEnrollmentDetails() {
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

    @Override
    public List<EnrollmentsDto> fetchEnrollmentsByCourseID(@RequestParam int courseID)
    {
        List<Enrollments> enrollments= enrollmentRepository.findAll();
        List<EnrollmentsDto> enrollmentsDtos= new ArrayList<>();
        for(Enrollments e:enrollments)
        {   if(e.getCourseID()==courseID) {
            EnrollmentsDto enroll = EnrollmentsMapper.mapToEnrollmentsDto(e, new EnrollmentsDto());
            enrollmentsDtos.add(enroll);
        }
        }
        return enrollmentsDtos;
    }

    @Override
    public List<EnrollmentsDto> fetchEnrollmentsByMentorID(int mentorID) {
        List<Enrollments> enrollments= enrollmentRepository.findAll();
        List<EnrollmentsDto> enrollmentsDtos= new ArrayList<>();
        for(Enrollments e:enrollments)
        {   if(e.getMentorID()==mentorID) {
            EnrollmentsDto enroll = EnrollmentsMapper.mapToEnrollmentsDto(e, new EnrollmentsDto());
            enrollmentsDtos.add(enroll);
        }
        }
        return enrollmentsDtos;
    }

    @Override
    public List<EnrollmentsDto> fetchEnrollmentsByNewJoinID(int newJoinID) {
        List<Enrollments> enrollments= enrollmentRepository.findAll();
        List<EnrollmentsDto> enrollmentsDtos= new ArrayList<>();
        for(Enrollments e:enrollments)
        {   if(e.getNewJoinID()==newJoinID) {
            EnrollmentsDto enroll = EnrollmentsMapper.mapToEnrollmentsDto(e, new EnrollmentsDto());
            enrollmentsDtos.add(enroll);
        }
        }
        return enrollmentsDtos;
    }
}
