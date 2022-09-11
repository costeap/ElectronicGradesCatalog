package com.example.demo.mapper;

import com.example.demo.dto.StudentDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Student;
import com.example.demo.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserMapper {
    public static UserDto mapToDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .actiune(user.getActiune())
                .timestamp(user.getTimestamp())
                .build();
    }

    public static User mapToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .actiune(userDto.getActiune())
                .timestamp(userDto.getTimestamp())
                .build();
    }

    public static List<UserDto> mapUserToDetails(List<User> users) {
        List<UserDto> dtos=new LinkedList<>();
        for(User user:users){
            dtos.add(UserDto.builder().id(user.getId()).username(user.getUsername()).actiune(user.getActiune()).timestamp(user.getTimestamp()).build());
        }
        return dtos;
    }
}
