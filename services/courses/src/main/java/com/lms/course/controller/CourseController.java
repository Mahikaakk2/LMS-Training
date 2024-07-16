package com.lms.course.controller;

import com.lms.course.dto.CourseDto;
import com.lms.course.dto.ResponseDto;
import com.lms.course.service.ICourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//controller and response body
@Validated
@RestController
@RequestMapping("/api")
//@AllArgsConstructor
public class CourseController {

    @Autowired
    private ICourseService iCourseService;

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
    public ResponseEntity<ResponseDto> updateCourse(@RequestParam int courseId, @Valid @RequestBody CourseDto courseDto) {
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

    //api to fetch all the courses present in the database
    @GetMapping("/fetchAllCourses")
    public ResponseEntity<List<CourseDto>> fetchAllCourses(){

        List<CourseDto> courses =iCourseService.fetchAllCourseDetails();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courses);
    }

    //api to fetch a particular course details using courseId
    @GetMapping("/fetchCourseById")
    public  ResponseEntity<CourseDto> fetchCouseById(@RequestParam int courseId){
        CourseDto courseDto=iCourseService.fetchCourseDetailById(courseId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseDto);
    }

    //api end point to get all the courses of a particular mentor or instructor
    @GetMapping("/getAllCourses")
    public ResponseEntity<List<CourseDto>> fetchCourseDetails(@RequestParam Long mentorId){

        List<CourseDto> courseDtoList=iCourseService.getAllCourseDetails(mentorId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseDtoList);
    }

    @GetMapping("/getAllCoursesByStatus")
    public ResponseEntity<List<CourseDto>> fetchCourseDetailsByMentorIDAStatus(@RequestParam Long createdBy,boolean isApproved){

        List<CourseDto> courseDtoList=iCourseService.getAllCourseDetailsByStatus(createdBy,isApproved);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseDtoList);
    }



    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Api is working");
    }

}
