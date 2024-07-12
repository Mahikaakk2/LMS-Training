package com.lms.course.service;

import com.lms.course.dto.CourseDto;

import java.util.List;

public interface ICourseService {
    List<CourseDto> fetchAllPendingCourseDetails();

    void createCourse(CourseDto courseDto);

    boolean approveCourse(int courseId);

    boolean updateCourse(int courseId, CourseDto courseDto);

    boolean deleteCourse(int courseId);

    //to fetch the details of all courses
    List<CourseDto> fetchAllCourseDetails();

    CourseDto fetchCourseDetailById(int courseId);

    List<CourseDto> getAllCourseDetails(int mentorId);

}
