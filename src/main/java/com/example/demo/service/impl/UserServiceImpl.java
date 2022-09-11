package com.example.demo.service.impl;

import com.example.demo.dto.UserActivityDto;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.MembruService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private final MembruService membruService;

    public UserServiceImpl(UserRepo userRepo, ModelMapper mapper, MembruService membruService) {
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.membruService = membruService;
    }

   @Transactional
    public UserDto addUser(UserDto userDto) {
       User user = UserMapper.mapToEntity(userDto);
       User save = userRepo.save(user);
       return UserMapper.mapToDTO(save);
    }

    @Transactional
    public List<UserDto> findAllUsers() {
        List<User> users = userRepo.findAll();
        return mapToDTOList(users);
    }


    @Transactional
    public List<UserDto> findUserActivity(String username)
    {
        return mapToDTOList(userRepo.findUserByUsername(username));
    }

    @Transactional
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepo.findUserById(id);
        user.setUsername(userDto.getUsername());
        user.setActiune(userDto.getActiune());
        user.setTimestamp(userDto.getTimestamp());
        User save = userRepo.save(user);
        return UserMapper.mapToDTO(user);
    }

    @Transactional
    public Integer usersConnected()
    {
        int login=0;
        int logout=0;
        for (User user:userRepo.findAll())
        {
            if (user.getActiune().equals("login"))
            {
                login++;
            }
            else if (user.getActiune().equals("logout"))
            {
                logout++;
            }
        }
        return login-logout;
    }

    public List<UserDto> mapToDTOList(List<User> users) {
        List<UserDto> dtos = users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
