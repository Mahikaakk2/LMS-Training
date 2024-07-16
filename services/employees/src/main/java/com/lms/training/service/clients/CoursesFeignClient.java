package com.lms.training.service.clients;


import com.lms.training.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("courses")
public interface CoursesFeignClient {
    @GetMapping("/api/getAllCoursesByStatus")
    public ResponseEntity<List<CourseDto>> fetchCourseDetailsByMentorIDAStatus(@RequestParam Long createdBy, boolean isApproved);

}


//
//@GetMapping("/getAllCoursesByStatus")
//public ResponseEntity<List<CourseDto>> fetchCourseDetailsByMentorIDAStatus(@RequestParam Long createdBy,boolean isApproved){
