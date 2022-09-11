package com.example.demo.controller;

import com.example.demo.dto.AdaugareNotaDto;
import com.example.demo.dto.CatalogDto;
import com.example.demo.dto.CatalogDtoVizualizare;
import com.example.demo.model.Catalog;
import com.example.demo.model.Student;
import com.example.demo.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Operation(summary = "Get all grades", responses = {
            @ApiResponse(description = "Get all grades (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<Catalog>> getAllNote() {
        return new ResponseEntity<>(catalogService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Update grade", responses = {
            @ApiResponse(description = "Grade updated (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })

    @PostMapping("/student/curs")
    @Transactional
    public ResponseEntity<Catalog> updateNota(@RequestBody @Validated AdaugareNotaDto catalogDto) {
        Catalog updateCatalog = catalogService.updateNota1(catalogDto, catalogDto.getStudent_id(), catalogDto.getCurs_id());
        return new ResponseEntity<>(updateCatalog, HttpStatus.OK);
    }

    @Operation(summary = "Delete grade by student_id and curs_id", responses = {
            @ApiResponse(description = "Grade deleted (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @DeleteMapping("/{student_id}/{curs_id}")
    @Transactional
    public ResponseEntity<?> deleteNota(@PathVariable("student_id") Long student_id, @PathVariable("curs_id") Long curs_id) {
        catalogService.deleteNota(student_id, curs_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Add grade", responses = {
            @ApiResponse(description = "Grade added (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @PostMapping()
    public ResponseEntity<Catalog> addNota(@RequestBody AdaugareNotaDto dto ) {
        Catalog newNota = catalogService.addNota(dto);
        return new ResponseEntity<>(newNota, HttpStatus.CREATED);
    }

    @Operation(summary = "Get grade by course name", responses = {
            @ApiResponse(description = "Get grade by course name (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping("/{numeCurs}")
    public ResponseEntity<?> getNoteByNumeCurs(@PathVariable("numeCurs") String numeCurs) {
        catalogService.descarcareNote(numeCurs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/xml/{numeCurs}")
    public ResponseEntity<?> genNoteXML(@PathVariable("numeCurs") String numeCurs) {
        catalogService.exportareNoteXML(numeCurs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get grade by student ID", responses = {
            @ApiResponse(description = "Get grade by student ID (succes)", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",content = @Content)
    })
    @GetMapping("/student/{student_id}")
    public ResponseEntity<List<CatalogDtoVizualizare>> getNoteByStudent(@PathVariable("student_id") Long student_id) {
        return new ResponseEntity<>(catalogService.vizualizareNote1(student_id), HttpStatus.OK);
    }

}
