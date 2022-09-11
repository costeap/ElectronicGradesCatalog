package com.example.demo.controller;

import com.example.demo.dto.ProfesorDto;
import com.example.demo.dto.ProfesorDto1;
import com.example.demo.dto.TrimitereEmailDto;
import com.example.demo.service.ProfesorService;
import com.example.demo.service.impl.EmailWithAttachServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    private final ProfesorService profesorService;
    private final EmailWithAttachServiceImpl emailWithAttachService;

    private final BCryptPasswordEncoder passwordEncoder;

    public ProfesorController(ProfesorService profesorService, EmailWithAttachServiceImpl emailWithAttachService) {
        this.profesorService = profesorService;
        this.emailWithAttachService = emailWithAttachService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Operation(summary = "Get all professors", responses = {
            @ApiResponse(description = "Get all professors (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<ProfesorDto>> getAllProfesori() {
        return new ResponseEntity<>(profesorService.findAllProfesori(), HttpStatus.OK);
    }

    @Operation(summary = "Get professor by ID", responses = {
            @ApiResponse(description = "Get professor by ID (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping("/{id}")
    //@CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<ProfesorDto> getProfesorById(@PathVariable("id") Long id) {
        ProfesorDto profesorDto = profesorService.findProfesorById(id);
        return new ResponseEntity<>(profesorDto, HttpStatus.OK);
    }

    @Operation(summary = "Update professor by ID", responses = {
            @ApiResponse(description = "Professor updated (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProfesorDto> updateProfesor(@Valid @RequestBody ProfesorDto1 profesorDto, @PathVariable Long id) {
        ProfesorDto updateProfesor = profesorService.updateProfesor1(profesorDto, id);
        return new ResponseEntity<>(updateProfesor, HttpStatus.OK);
    }

    @Operation(summary = "Delete professor by ID", responses = {
            @ApiResponse(description = "Professor deleted (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping("/prof/{id}")
    @Transactional
    public ResponseEntity<?> deleteProfesor(@PathVariable("id") String id) {
        profesorService.deleteProfesor(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Add professor", responses = {
            @ApiResponse(description = "Professor added (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @PostMapping()
    public ResponseEntity<ProfesorDto> addProfesor(@Valid @RequestBody ProfesorDto dto) {
        System.out.println(dto);
        ProfesorDto newProfesor = profesorService.addProfesor(dto);
        return new ResponseEntity<>(newProfesor, HttpStatus.CREATED);
    }

    @Operation(summary = "Send email", responses = {
            @ApiResponse(description = "Email sent(succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @PostMapping("/admin")
    public ResponseEntity<?>trimitereEmail(@RequestBody TrimitereEmailDto dto)
    {
        String toEmail=dto.getToEmail();
        String body=dto.getBody();
        String subject=dto.getSubject();
        profesorService.trimitereEmail(toEmail, body, subject);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/send-mail")
    public ResponseEntity<String> sendMail(@RequestBody TrimitereEmailDto emailRequest) throws MessagingException {
        emailWithAttachService.sendMailWithAttachment("costeapaula151@gmail.com",
                "This is email body.",
                "This email subject", "" +
                        "C:\\Users\\paula\\Desktop\\Copie copie\\project-costeap-main\\note.xml")
        ;
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
