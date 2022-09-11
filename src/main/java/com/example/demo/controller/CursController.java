package com.example.demo.controller;

import com.example.demo.Validators.RestExceptionHandler;
import com.example.demo.dto.CursDto;
import com.example.demo.dto.CursDto1;
import com.example.demo.dto.ProfesorDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Curs;
import com.example.demo.model.Student;
import com.example.demo.service.CursService;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.catalina.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/curs")
public class CursController {
    private final CursService cursService;

    public CursController(CursService cursService) {
        this.cursService = cursService;
    }

    @Operation(summary = "Get all courses", responses = {
            @ApiResponse(description = "Get all courses (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<CursDto>> getAllCursuri() {
        return new ResponseEntity<>(cursService.findAllCursuri(), HttpStatus.OK);
    }

    @Operation(summary = "Get course by ID", responses = {
            @ApiResponse(description = "Get course by ID (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CursDto> getCursById(@PathVariable("id") Long id) {
        CursDto cursDto = cursService.findCursById(id);
        return new ResponseEntity<>(cursDto, HttpStatus.OK);
    }

    @Operation(summary = "Update course by ID", responses = {
            @ApiResponse(description = "Course updated (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CursDto> updateCurs(@Valid @RequestBody CursDto1 cursDto, @PathVariable Long id) {
        CursDto updateCurs = cursService.updateCurs1(cursDto, id);
        return new ResponseEntity<>(updateCurs, HttpStatus.OK);
    }

    @Operation(summary = "Delete course by ID", responses = {
            @ApiResponse(description = "Course deleted (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping("/curs/{id}")
    @Transactional
    public ResponseEntity<?> deleteCurs(@PathVariable("id") Long id) {
        cursService.deleteCurs(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Add course", responses = {
            @ApiResponse(description = "Course added (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })

    @PostMapping()
    public ResponseEntity<CursDto> addCurs(@Valid @RequestBody CursDto dto) {
        System.out.println(dto);
        CursDto newCurs = cursService.addCurs(dto);
        return new ResponseEntity<>(newCurs, HttpStatus.CREATED);
    }
}
