package com.lms.training.mapper;

import com.lms.training.dto.UserDto;
import com.lms.training.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user, UserDto userDto){

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setMobileNumber(user.getMobileNumber());

        return userDto;
    }

    public static User mapToUser(UserDto userDto,User user){
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setMobileNumber(userDto.getMobileNumber());

        return user;
    }



}
