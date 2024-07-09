package com.lms.training.controller;


import com.lms.training.dto.ResponseDto;
import com.lms.training.dto.UserDto;
import com.lms.training.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto
    ){
        iUserService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201","Created Successfully"));
    }
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
