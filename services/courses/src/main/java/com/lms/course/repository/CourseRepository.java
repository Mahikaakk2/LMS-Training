package com.lms.course.repository;

import com.lms.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    List<Course> findByIsApprovedFalse();

    List<Course> findBycreatedBy(int mentorId);

    List<Course> findByCreatedByAndIsApproved(int mentorId, boolean isApproved);
}
