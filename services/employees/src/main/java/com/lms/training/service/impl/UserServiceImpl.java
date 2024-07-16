package com.lms.training.service.impl;

import com.lms.training.dto.UserDto;
import com.lms.training.dto.CourseDto;
import com.lms.training.entity.User;
import com.lms.training.exception.MentorDeletionException;
import com.lms.training.exception.MentorNotFindException;
import com.lms.training.exception.ResourceNotFoundException;
import com.lms.training.exception.UserAlreadyExistsException;
import com.lms.training.mapper.UserMapper;
import com.lms.training.repository.UserRepository;
import com.lms.training.service.IUserService;
import com.lms.training.service.clients.CoursesFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final CoursesFeignClient coursesFeignClient;


    @Override
    public void createUser(UserDto userdto) {

        Optional<User> foundUser=userRepository.findByEmail(userdto.getEmail());
        if(foundUser.isPresent()){
            throw new UserAlreadyExistsException("User already exists for this email : "+userdto.getEmail());
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

    @Override
    public boolean updateUserDetails(String email,UserDto userDto){
        boolean isUpdated=false;
        User user=userRepository.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("User","email",email));
        if(user!=null) {
            User users = userRepository.findByUserId(user.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", user.getUserId().toString()));
            User updatedUser= UserMapper.mapToUser(userDto, user);
            userRepository.save(updatedUser);

            isUpdated=true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteUser(String email){
        boolean isDeleted=false;
        User user=userRepository.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("User","email",email)
        );

        if(user!=null) {
            if(user.getRole().equals("newJoiner")) {
                userRepository.deleteById(user.getUserId());
                isDeleted=true;
            }
            else{
                throw new MentorDeletionException("Cannot delete user because user is a mentor.");
            }

        }
        return isDeleted;
    }

    @Override
    public List<CourseDto> fetchAllCoursesByStatus(Long createdBy, boolean isApproved) {
        List<UserDto> usersDtos = fetchAllUsersByRole("mentor");

        List<User> users=new ArrayList<>();

        for(UserDto userdto : usersDtos) {
                User user= UserMapper.mapToUser(userdto,new User());
                users.add(user);
        }

        List<CourseDto> courseDtos=new ArrayList<>();
        boolean flag=false;
        for(User user : users) {
            if(user.getUserId().equals(createdBy)) {
                courseDtos = (List<CourseDto>) coursesFeignClient.fetchCourseDetailsByMentorIDAStatus(createdBy,isApproved);
                flag=true;
                break;
            }
        }
        if(!flag) {
            throw new MentorNotFindException("Cannot find the mentor.");
        }
        return courseDtos;
    }



}
