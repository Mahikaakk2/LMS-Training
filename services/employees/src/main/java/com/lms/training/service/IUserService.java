package com.lms.training.service;

import com.lms.training.dto.UserDto;

import java.util.List;

public interface IUserService {
    void createUser(UserDto userDto);
    UserDto fetchUserDetails(String email);
    List<UserDto> fetchAllUsers();
}
