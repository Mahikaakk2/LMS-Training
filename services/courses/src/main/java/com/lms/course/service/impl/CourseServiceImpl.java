package com.lms.course.service.impl;

import com.lms.course.dto.CourseDto;
import com.lms.course.entity.Course;
import com.lms.course.exception.ResourceNotFoundException;
import com.lms.course.mapper.CourseMapper;
import com.lms.course.repository.CourseRepository;
import com.lms.course.service.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseDto> fetchAllPendingCourseDetails() {
        List<Course> pendingCourses = courseRepository.findByIsApprovedFalse();
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : pendingCourses) {
            CourseDto courseDto = new CourseDto();
            CourseMapper.mapToCoursesDto(course, courseDto);
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    @Override
    public void createCourse(CourseDto courseDto) {
        Course course = CourseMapper.mapToCourse( courseDto,new Course());
        course.setCreatedAt((LocalDateTime.now()));
        course.setCreatedBy(000);
        courseRepository.save(course);
    }

    @Override
    public boolean approveCourse(int courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            if (!course.isApproved()) {
                course.setApproved(true);
                courseRepository.save(course);
                return true;
            } else {
                return false; //course already tue
            }
        } else {
                //if course is not found then throw the exception
                throw new ResourceNotFoundException("Course with ID " + courseId + " not found");
        }
    }

    @Override
    public boolean updateCourse(int courseId, CourseDto courseDto) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course existingCourse = optionalCourse.get();

            Course updatedCourse = CourseMapper.mapToCourse(courseDto, existingCourse);
            courseRepository.save(updatedCourse);
            return true;

        } else {
                throw new ResourceNotFoundException("Course with ID " + courseId + " not found");
        }
    }

    @Override
    public boolean deleteCourse(int courseId) {
        boolean isDeleted=false;
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            courseRepository.deleteById(courseId);
            isDeleted=true;
        } else {
                throw new ResourceNotFoundException("Course with ID " + courseId + " not found");
        }
        return isDeleted;
    }

    //this method is returning all the present courses in database
    @Override
    public List<CourseDto> fetchAllCourseDetails() {

        List<Course> courses=courseRepository.findAll();
        return courses.stream()
                .map(course -> CourseMapper.mapToCoursesDto(course, new CourseDto())) // Map each Course entity to CourseDto
                .collect(Collectors.toList()); // Collect and return the list of CourseDto
    }

    @Override
    public CourseDto fetchCourseDetailById(int courseId) {
        Optional<Course> optionalCourse=courseRepository.findById(courseId);
        //in case no course is found corresponding to this given id
        if (optionalCourse.isEmpty()) {
            throw new ResourceNotFoundException("Course with ID " + courseId + " not found");
        }
        Course course=optionalCourse.get();
        CourseDto courseDto=CourseMapper.mapToCoursesDto(course,new CourseDto());
        return courseDto;
    }

}
