package com.lms.training.mapper;

import com.lms.training.Dto.EnrollmentsDto;
import com.lms.training.entity.Enrollments;

public class EnrollmentsMapper {

    public static EnrollmentsDto mapToEnrollmentsDto(Enrollments enrollments,EnrollmentsDto enrollmentsDto)
    {
        //enrollmentsDto.setEnrollmentID(enrollments.getEnrollmentID());
        enrollmentsDto.setCourseID(enrollments.getCourseID());
        enrollmentsDto.setMentorID(enrollments.getMentorID());
        enrollmentsDto.setNewJoinID(enrollments.getNewJoinID());
        return enrollmentsDto;
    }
    public static Enrollments mapToEnrollments(EnrollmentsDto enrollmentsDto, Enrollments enrollments)
    {
        //enrollments.setEnrollmentID(enrollmentsDto.getEnrollmentID());
        enrollments.setCourseID(enrollmentsDto.getCourseID());
        enrollments.setMentorID(enrollmentsDto.getMentorID());
        enrollments.setNewJoinID(enrollmentsDto.getNewJoinID());
        return enrollments;
    }
}
