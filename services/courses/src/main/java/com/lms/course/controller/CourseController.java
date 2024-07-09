package com.lms.course.controller;

import com.lms.course.service.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//controller and response body
@Validated
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CourseController {
    private final ICourseService iCourseService;

    //api's
//    create single course;
//    fetch single course by id;
//    fetch all;
//    fetch all pending courses;
//
//    update by course id;
//    approve pending course by id;
//
//    delete single course by id;


}
