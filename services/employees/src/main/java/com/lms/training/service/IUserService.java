package com.lms.training.service;

import com.lms.training.dto.UserDto;
import com.lms.training.dto.CourseDto;

import java.util.List;

public interface IUserService {
    void createUser(UserDto userDto);
    UserDto fetchUserDetails(String email);
    List<UserDto> fetchAllUsers();
    List<UserDto> fetchAllUsersByRole(String role);
    boolean updateUserDetails(String email,UserDto userDto);
    boolean deleteUser(String email);
    List<CourseDto> fetchAllCoursesByStatus(Long createdBy,boolean isApproved);
}
