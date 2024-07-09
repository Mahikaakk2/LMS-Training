package com.lms.course.controller;

import com.lms.course.dto.CourseDto;
import com.lms.course.dto.ResponseDto;
import com.lms.course.service.ICourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//controller and response body
@Validated
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CourseController {
    private final ICourseService iCourseService;

//    delete single course by id;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CourseDto courseDto){
        iCourseService.createCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201","Created Sucessfully"));
    }

    @GetMapping("/fetchAllPending")
    public List<CourseDto> fetchAll(){
        List<CourseDto> courseDto= iCourseService.fetchAllPendingCourseDetails();
        return courseDto;
    }

    @PutMapping("/approve")
    public ResponseEntity<ResponseDto> approveCourse(@RequestParam int courseId) {
        boolean isApproved = iCourseService.approveCourse(courseId);
        if (isApproved) {
            ResponseDto responseDto = new ResponseDto("200", "Course approved successfully");
            return ResponseEntity.ok(responseDto);
        } else {
            ResponseDto responseDto = new ResponseDto("404", "Course not found or already approved");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCourse(@RequestParam int courseId, @RequestBody CourseDto courseDto) {
        boolean isUpdated = iCourseService.updateCourse(courseId, courseDto);
        if (isUpdated) {
            ResponseDto responseDto = new ResponseDto("200", "Course updated successfully");
            return ResponseEntity.ok(responseDto);
        } else {
            ResponseDto responseDto = new ResponseDto("404", "Course not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCourse(@RequestParam int courseId) {
        boolean isDeleted = iCourseService.deleteCourse(courseId);
        if (isDeleted) {
            ResponseDto responseDto = new ResponseDto("200", "Course deleted successfully");
            return ResponseEntity.ok(responseDto);
        } else {
            ResponseDto responseDto = new ResponseDto("404", "Course not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }


}
