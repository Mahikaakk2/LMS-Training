package com.lms.course.mapper;

import com.lms.course.dto.CourseDto;
import com.lms.course.entity.Course;

public class CourseMapper {

    public static CourseDto mapToCoursesDto(Course course , CourseDto courseDto){
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseTitle(course.getCourseTitle());
        courseDto.setCourseDescription(course.getCourseDescription());
        courseDto.setCourseDuration(course.getCourseDuration());
        courseDto.setCourseResource(course.getCourseResource());
        courseDto.setCourseCategory(course.getCourseCategory());
        courseDto.setApproved(course.isApproved());
        return courseDto;
    }

    public static Course mapToCourse(CourseDto courseDto , Course course){
        course.setCourseId(courseDto.getCourseId());
        course.setCourseDescription(courseDto.getCourseDescription());
        course.setCourseTitle(courseDto.getCourseTitle());
        course.setCourseDuration(courseDto.getCourseDuration());
        course.setCourseResource(courseDto.getCourseResource());
        course.setCourseCategory(courseDto.getCourseCategory());
        course.setApproved(courseDto.isApproved());
        return course;
    }


}
