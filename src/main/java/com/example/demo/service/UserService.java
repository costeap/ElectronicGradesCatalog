package com.example.demo.service;

import com.example.demo.dto.ProfesorDto;
import com.example.demo.dto.ProfesorDto1;
import com.example.demo.dto.UserActivityDto;
import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto addUser(UserDto userDto);
    List<UserDto> findAllUsers();
    List<UserDto> findUserActivity(String username);
    UserDto updateUser(UserDto userDto, Long id);
    Integer usersConnected();
}
