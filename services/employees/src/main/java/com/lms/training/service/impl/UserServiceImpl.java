package com.lms.training.service.impl;

import com.lms.training.dto.UserDto;
import com.lms.training.entity.User;
import com.lms.training.exception.UserAlreadyExistsException;
import com.lms.training.mapper.UserMapper;
import com.lms.training.repository.UserRepository;
import com.lms.training.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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



}
