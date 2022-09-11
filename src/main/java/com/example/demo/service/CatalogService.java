package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Catalog;
import com.example.demo.model.Curs;
import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    Catalog addNota(AdaugareNotaDto dto);
    Catalog addCursInCatalog(AdaugareCursInCatalogDto dto);
    List<Catalog> findAll();
    Catalog updateNota(CatalogDto catalogDto, Long student_id, Long Curs_id);
    Catalog updateNota1(AdaugareNotaDto catalogDto, Long student_id, Long Curs_id);
    void deleteNota(Long student_id, Long curs_id);
    void descarcareNote(String numeCurs);
    String exportareNoteXML(String numeCurs);
    List<Catalog> vizualizareNote(Long student_id);
    List<CatalogDtoVizualizare> vizualizareNote1(Long student_id);

}
