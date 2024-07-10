package com.lms.training.repository;

import com.lms.training.entity.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollments, Integer> {

    //Optional<Enrollments> findByNewJoinId(int newJoinId);
}
