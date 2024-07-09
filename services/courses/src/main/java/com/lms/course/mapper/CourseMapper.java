package com.lms.course.mapper;

import com.lms.course.dto.CourseDto;
import com.lms.course.entity.Course;

public class CourseMapper {

    public static CourseDto mapToCoursesDto(Course course , CourseDto courseDto){
        courseDto.setCourseTitle(course.getCourseTitle());
        courseDto.setCourseDescription(course.getCourseDescription());
        courseDto.setCourseDuration(course.getCourseDuration());
        courseDto.setCourseResource(course.getCourseResource());
        courseDto.setCourseCategory(course.getCourseCategory());
        return courseDto;
    }

    public static Course mapToCourse(CourseDto courseDto , Course course){
        course.setCourseDescription(courseDto.getCourseDescription());
        course.setCourseTitle(courseDto.getCourseTitle());
        course.setCourseDuration(courseDto.getCourseDuration());
        course.setCourseResource(courseDto.getCourseResource());
        course.setCourseCategory(courseDto.getCourseCategory());
        return course;
    }


}
