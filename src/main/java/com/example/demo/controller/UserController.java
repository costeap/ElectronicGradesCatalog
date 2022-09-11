package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<List<UserDto>> getUsersByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findUserActivity(username), HttpStatus.OK);
    }

    @GetMapping("/connected")
    public ResponseEntity<Integer> getUsersConnected() {
        return new ResponseEntity<>(userService.usersConnected(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        UserDto updateUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) {
        UserDto newUser = userService.addUser(dto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
