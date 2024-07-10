package com.lms.training.controller;


import com.lms.training.dto.ResponseDto;
import com.lms.training.dto.UserDto;
import com.lms.training.service.IUserService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto)
    {
        iUserService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201","Created Successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<UserDto> fetchUser(@RequestParam String email){
        UserDto userDto= iUserService.fetchUserDetails(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<UserDto>> fetchAll() {
        List<UserDto> userDto= iUserService.fetchAllUsers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @GetMapping("/fetchByRole")
    public ResponseEntity<List<UserDto>> fetchAllUsersByRole(@RequestParam String role) {
        List<UserDto> userDto= iUserService.fetchAllUsersByRole(role);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestBody UserDto userDto,
                                              @RequestParam String email) {
        boolean isUpdated=iUserService.updateUserDetails(email,userDto);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("204", "Updated Successfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto("500", "Not Updated"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam String email) {
        boolean isDeleted=iUserService.deleteUser(email);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("204", "Deleted Successfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto("500", "Not Deleted"));
        }
    }
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
