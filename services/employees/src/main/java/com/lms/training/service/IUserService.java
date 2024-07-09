package com.lms.training.service;

import com.lms.training.dto.UserDto;

public interface IUserService {
    void createUser(UserDto userDto);
    UserDto fetchUserDetails(String email);
}
