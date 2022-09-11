package com.example.demo.service.impl;

import com.example.demo.dto.AdaugareCursInCatalogDto;
import com.example.demo.dto.AdaugareNotaDto;
import com.example.demo.dto.CatalogDto;
import com.example.demo.dto.CatalogDtoVizualizare;
import com.example.demo.mapper.CatalogMapper;
import com.example.demo.model.*;
import com.example.demo.repo.CatalogRepo;
import com.example.demo.repo.CursRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.CatalogService;
import com.example.demo.utils.FileWriterr;
import com.example.demo.utils.NotificationEndpoints;
import com.example.demo.utils.exporter.FileExporter;
import com.example.demo.utils.exporter.XMLFileExporter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepo catalogRepo;
    private final ModelMapper mapper;
    private final StudentRepo studentRepo;
    private final CursRepo cursRepo;
    private final SimpMessagingTemplate template;


    @Autowired
    public CatalogServiceImpl(CatalogRepo catalogRepo, ModelMapper mapper, StudentRepo studentRepo, CursRepo cursRepo, SimpMessagingTemplate template) {
        this.catalogRepo = catalogRepo;
        this.mapper = mapper;
        this.studentRepo = studentRepo;
        this.cursRepo = cursRepo;
        this.template = template;
    }

    @Transactional
    public Catalog addNota(AdaugareNotaDto dto) {
        Student student=studentRepo.findStudentById(dto.getStudent_id());
        Curs curs=cursRepo.findCursById(dto.getCurs_id());

        Catalog save=new Catalog();
        String numeProfesor="";
        String cnpProfesor="";
        List<Profesor> listaProfesori=curs.getListaProfesori();
        for(Profesor p:listaProfesori)
        {
            numeProfesor=p.getNume();
            cnpProfesor=p.getCnp();
        }
        int nota=dto.getNota();
        if (dto.getNumeProfesor().equals(numeProfesor) && dto.getCnpProfesor().equals(cnpProfesor)) {
            Catalog catalog = new Catalog(student, curs, nota);
            save = catalogRepo.save(catalog);
        }
        this.template.convertAndSend(NotificationEndpoints.STUDENT_GRADE_ADDITION,
                "A grade was added at student with id: "+dto.getStudent_id());
        return save;
    }

    @Transactional
    public List<Catalog> findAll() {
        List<Catalog> catalog = catalogRepo.findAll();
        return catalog;
    }

    @Transactional
    public Catalog updateNota(CatalogDto catalogDto, Long student_id, Long curs_id) {
        Catalog catalog=catalogRepo.findNotaByStudentAndCurs(studentRepo.findStudentById(student_id), cursRepo.findCursById(curs_id));
        Curs curs=cursRepo.findCursById(catalogDto.getCurs_id());
        catalog.setCurs(curs);
        catalog.setNota(catalogDto.getNota());
        Catalog save=catalogRepo.save(catalog);
        return save;
    }

    @Transactional
    public Catalog updateNota1(AdaugareNotaDto catalogDto, Long student_id, Long curs_id) {
        Student student=studentRepo.findStudentById(catalogDto.getStudent_id());
        Curs curs=cursRepo.findCursById(catalogDto.getCurs_id());

        Catalog save=new Catalog();
        String numeProfesor="";
        String cnpProfesor="";
        List<Profesor> listaProfesori=curs.getListaProfesori();
        for(Profesor p:listaProfesori)
        {
            numeProfesor=p.getNume();
            cnpProfesor=p.getCnp();
        }
        int nota=catalogDto.getNota();
        if (catalogDto.getNumeProfesor().equals(numeProfesor) && catalogDto.getCnpProfesor().equals(cnpProfesor)) {
            Catalog catalog = catalogRepo.findNotaByStudentAndCurs(studentRepo.findStudentById(student_id), cursRepo.findCursById(curs_id));
            Curs curs1 = cursRepo.findCursById(catalogDto.getCurs_id());
            catalog.setCurs(curs1);
            catalog.setNota(catalogDto.getNota());
            Catalog save1 = catalogRepo.save(catalog);
            return save1;

        }
        return null;
    }

    @Transactional
    public void deleteNota(Long student_id, Long curs_id) {
        catalogRepo.deleteNotaByStudentAndCurs(studentRepo.findStudentById(student_id), cursRepo.findCursById(curs_id));
    }

    @Transactional
    public void descarcareNote(String numeCurs){
        Curs curs=cursRepo.findCursByNumeCurs(numeCurs);
        List<Catalog> note=catalogRepo.findByCurs(curs);
        FileWriterr.writeFile(note, curs);
    }

    @Transactional
    public String exportareNoteXML(String numeCurs){
        Curs curs=cursRepo.findCursByNumeCurs(numeCurs);
        List<Catalog> note=catalogRepo.findByCurs(curs);
        //Catalog catalog=note.get(0);
        FileExporter fileExporter;
        fileExporter=new XMLFileExporter();
        GradesList noteList=new GradesList();
        noteList.setListaNote(note);
        return fileExporter.exportData(noteList);
    }

    @Transactional
    public List<Catalog> vizualizareNote(Long student_id){
        Student student=studentRepo.findStudentById(student_id);
        List<Catalog> note=catalogRepo.findByStudent(student);
        return note;
    }

    @Transactional
    public List<CatalogDtoVizualizare> vizualizareNote1(Long student_id)
    {
        Student student=studentRepo.findStudentById(student_id);
        List<CatalogDtoVizualizare> note=new ArrayList<>();
        for (Catalog c:catalogRepo.findByStudent(student))
        {
            note.add(CatalogMapper.mapToDTO(c));
        }
        return note;
    }

    public Catalog addCursInCatalog(AdaugareCursInCatalogDto dto)
    {
        Student student=studentRepo.findStudentById(dto.getStudent_id());
        Curs curs=cursRepo.findCursById(dto.getCurs_id());

        Catalog save=new Catalog();

        int nota=dto.getNota();

        Catalog catalog = new Catalog(student, curs, nota);
        save = catalogRepo.save(catalog);

        return save;
    }


}
