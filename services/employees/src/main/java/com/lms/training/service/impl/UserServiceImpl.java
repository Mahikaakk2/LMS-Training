package com.lms.training.service.impl;

import com.lms.training.dto.UserDto;
import com.lms.training.entity.User;
import com.lms.training.exception.ResourceNotFoundException;
import com.lms.training.exception.UserAlreadyExistsException;
import com.lms.training.mapper.UserMapper;
import com.lms.training.repository.UserRepository;
import com.lms.training.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;


    @Override
    public void createUser(UserDto userdto) {

        Optional<User> foundUser=userRepository.findByEmail(userdto.getEmail());
        if(foundUser.isPresent()){
            throw new UserAlreadyExistsException("User already exists for this mobile number"+userdto.getEmail());
        }
        User user= UserMapper.mapToUser(userdto,new User());
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Random");
        userRepository.save(user);
    }

    @Override
    public UserDto fetchUserDetails(String email){
        User foundUser = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User","email",email));

        UserDto userDto= UserMapper.mapToUserDto(foundUser,new UserDto());


        return userDto;
    }

    @Override
    public List<UserDto> fetchAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();

        for(User user : users) {
            UserDto userDto= UserMapper.mapToUserDto(user,new UserDto());
            userDtos.add(userDto);

        }
        return userDtos;
    }

    @Override
    public List<UserDto> fetchAllUsersByRole(String role) {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();

        for(User user : users) {
            if(user.getRole().equals(role)) {
                UserDto userDto= UserMapper.mapToUserDto(user,new UserDto());
                userDtos.add(userDto);
            }

        }
        return userDtos;
    }

}
