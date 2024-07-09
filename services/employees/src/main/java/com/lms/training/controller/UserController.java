package com.lms.training.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
