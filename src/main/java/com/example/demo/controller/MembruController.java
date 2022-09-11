package com.example.demo.controller;

import com.example.demo.dto.MembruDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Membru;
import com.example.demo.service.MembruService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;

@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.NONE)
@CrossOrigin
@RestController
@RequestMapping("/login")
public class MembruController {
    private final MembruService membruService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public MembruController(MembruService membruService, UserService userService) {
        this.membruService = membruService;

        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody MembruDto auth){
        UserDto dto=new UserDto();
        dto.setUsername(auth.getUsername());
        dto.setActiune("login");
        dto.setTimestamp(Timestamp.from(Instant.now()));
        userService.addUser(dto);
        String dtype=membruService.studentProfesor(auth);
        System.out.println(dtype);


        Membru membru=membruService.findByUsername(auth.getUsername());

        System.out.println(membru.getUsername());

        System.out.println(passwordEncoder.matches(auth.getParola(), membru.getParola()));

        if (membru==null)
        {
            return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
        }
        else if (!passwordEncoder.matches(auth.getParola(), membru.getParola()))
        {
            return new ResponseEntity("Wrong pass", HttpStatus.BAD_REQUEST);
        }

        if (dtype.equals("profesor")) {
            return new ResponseEntity(membru, HttpStatus.OK);
        }
        else if (dtype.equals("student")) {
            return new ResponseEntity(membru, HttpStatus.valueOf(201));
        }
        return new ResponseEntity(membru, HttpStatus.valueOf(202));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody MembruDto auth){
        UserDto dto=new UserDto();
        dto.setUsername(auth.getUsername());
        dto.setActiune("logout");
        dto.setTimestamp(Timestamp.from(Instant.now()));
        userService.addUser(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{username}")
    public ResponseEntity<Membru> getMembruByUsername(@PathVariable("username") String username) {
        Membru membru = membruService.findByUsername(username);
        return new ResponseEntity<>(membru, HttpStatus.OK);
    }

}
