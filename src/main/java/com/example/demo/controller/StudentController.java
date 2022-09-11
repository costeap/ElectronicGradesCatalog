package com.example.demo.controller;

import com.example.demo.dto.AdaugareCursInCatalogDto;
import com.example.demo.dto.CursDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.event.BaseEvent;
import com.example.demo.service.StudentService;
//import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final SimpMessagingTemplate messagingTemplate;

    public StudentController(StudentService studentService, SimpMessagingTemplate messagingTemplate) {
        this.studentService = studentService;
        this.messagingTemplate = messagingTemplate;
    }

    @Operation(summary = "Get all students", responses = {
            @ApiResponse(description = "Get all students succes", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAllStudenti() {
        return new ResponseEntity<>(studentService.findAllStudenti(), HttpStatus.OK);
    }

    @GetMapping("/cursuri/{id}")
    public ResponseEntity<List<CursDto>> getNewCourses(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.adaugareCursuri(id), HttpStatus.OK);
    }

    @Operation(summary = "Get student by ID", responses = {
            @ApiResponse(description = "Get student by ID succes", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping("/{id}")
    //@CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
        StudentDto studentDto = studentService.findStudentById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @Operation(summary = "Update by ID", responses = {
            @ApiResponse(description = "Student updated (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable Long id) {
        StudentDto updateStudent = studentService.updateStudent(studentDto, id);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @Operation(summary = "Delete by ID", responses = {
            @ApiResponse(description = "Student deleted (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping("/stud/{id}")
    @Transactional
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Add student", responses = {
            @ApiResponse(description = "Student added (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @PostMapping()
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto dto) {
        StudentDto newStudent = studentService.addStudent(dto);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PostMapping("/curs/catalog/{student_id}/{curs_id}/{nota}")
    public ResponseEntity<?> addCursInCatalog(@PathVariable("student_id") Long student_id, @PathVariable("curs_id") Long curs_id, @PathVariable("nota") int nota) {
        AdaugareCursInCatalogDto dto=new AdaugareCursInCatalogDto(student_id, curs_id, nota);
        studentService.adaugareCursuriInCatalog(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @EventListener(BaseEvent.class)
    public void handleEvent(BaseEvent event)
    {
        log.info("Got an event: {}.", event);
        messagingTemplate.convertAndSend("/topic/events", event);
    }
}
