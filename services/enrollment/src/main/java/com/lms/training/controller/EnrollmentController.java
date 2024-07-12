package com.lms.training.controller;

import com.lms.training.Dto.EnrollmentsDto;
import com.lms.training.Dto.ResponseDto;
import com.lms.training.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EnrollmentController {
    //reference of enrollment service
    private final EnrollmentService enrollmentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Validated @RequestBody EnrollmentsDto enrollmentsDto){

        enrollmentService.createEnrollment(enrollmentsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Created Successfully"));
    }

    //get all customers
    @GetMapping("/fetchAll")
    public ResponseEntity<List<EnrollmentsDto>> fetchAllAccount(){
        List<EnrollmentsDto> enrollmentsDtos = enrollmentService.fetchAllEnrollmentDetails();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(enrollmentsDtos);
    }

    //get all customers
    @GetMapping("/fetchByCourse")
    public ResponseEntity<List<EnrollmentsDto>> fetchEnrollByCourse(@RequestParam int courseID){
        List<EnrollmentsDto> enrollmentsDtos = enrollmentService.fetchEnrollmentsByCourseID(courseID);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(enrollmentsDtos);
    }
    @GetMapping("/fetchByMentor")
    public ResponseEntity<List<EnrollmentsDto>> fetchEnrollByMentor(@RequestParam int mentorID){
        List<EnrollmentsDto> enrollmentsDtos = enrollmentService.fetchEnrollmentsByMentorID(mentorID);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(enrollmentsDtos);
    }
    @GetMapping("/fetchByNewJoiner")
    public ResponseEntity<List<EnrollmentsDto>> fetchEnrollByNewJoiner(@RequestParam int newJoinID){
        List<EnrollmentsDto> enrollmentsDtos = enrollmentService.fetchEnrollmentsByNewJoinID(newJoinID);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(enrollmentsDtos);
    }
    
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {

        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
