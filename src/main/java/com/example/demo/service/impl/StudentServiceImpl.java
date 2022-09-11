package com.example.demo.service.impl;

import com.example.demo.dto.AdaugareCursInCatalogDto;
import com.example.demo.dto.CursDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.event.StudentCreatedEvent;
import com.example.demo.mapper.CursMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Catalog;
import com.example.demo.model.Curs;
import com.example.demo.model.Student;
import com.example.demo.repo.CatalogRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.CatalogService;
import com.example.demo.service.CursService;
import com.example.demo.service.StudentService;
import com.example.demo.utils.NotificationEndpoints;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final ModelMapper mapper;
    private final CatalogRepo catalogRepo;
    private final CatalogService catalogService;
    private final CursService cursService;
    private final SimpMessagingTemplate template;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, ModelMapper mapper, CatalogRepo catalogRepo, CatalogService catalogService, CursService cursService, SimpMessagingTemplate template, ApplicationEventPublisher eventPublisher) {
        this.studentRepo = studentRepo;
        this.mapper = mapper;
        this.catalogRepo = catalogRepo;
        this.catalogService = catalogService;
        this.cursService = cursService;
        this.template = template;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

     public StudentDto addStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToEntity(studentDto);

        String encodePassword=passwordEncoder.encode(studentDto.getParola());

        student.setParola(encodePassword);

        Student save = studentRepo.save(student);
         this.template.convertAndSend(NotificationEndpoints.STUDENT_ADDITION,
                 "Admin has added student with id: "+student.getId());

         StudentDto output = StudentMapper.mapToDTO(save);
         eventPublisher.publishEvent(new StudentCreatedEvent(output));
         return output;
    }



    public List<StudentDto> findAllStudenti() {
        List<Student> studenti = studentRepo.findAll();
        return StudentMapper.mapStudentToDetails(studenti);

    }

    public StudentDto updateStudent(StudentDto studentDto, Long id) {

        Student student = studentRepo.findStudentById(id);
        student.setNume(studentDto.getNume());
        student.setEmail(studentDto.getEmail());
        student.setCnp(studentDto.getCnp());
        student.setAnStudiu(studentDto.getAnStudiu());
        student.setUsername(studentDto.getUsername());
        student.setParola(studentDto.getParola());
        Student save = studentRepo.save(student);
        return StudentMapper.mapToDTO(student); ////
    }

    public Student updateStudent(Student student1, Long id) {

        Student student = studentRepo.findStudentById(id);
        student.setNume(student1.getNume());
        student.setEmail(student1.getEmail());
        student.setCnp(student1.getCnp());
        student.setAnStudiu(student1.getAnStudiu());
        student.setUsername(student1.getUsername());
        student.setParola(student1.getParola());
        Student save = studentRepo.save(student);
        return save;
    }

    public StudentDto findStudentById(Long id) {
        Student student = studentRepo.findStudentById(id);
        return StudentMapper.mapToDTO(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        catalogRepo.deleteAllByStudent(StudentMapper.mapToEntity(findStudentById(id)));
        studentRepo.deleteStudentById(id);
    }

    @Transactional
    public List<CursDto> adaugareCursuri(Long student_id){
        List<Catalog> note=new ArrayList<>();
        List<CursDto> cursuriDto=new ArrayList<>();
        List<Curs> cursuri=new ArrayList<>();
        List<Curs> cursuriNoi=new ArrayList<>();
        note= catalogService.vizualizareNote(student_id);
        cursuriDto=cursService.findAllCursuri();
        cursuri=CursMapper.mapDetailsToCurs(cursuriDto);
        for (Curs curs:cursuri)
        {
            boolean sem=false;
            for (Catalog nota:note)
            {
                if (curs.getId().equals(nota.getCurs().getId()))
                {
                    sem=true;
                }
            }
            int anStudiuCurs=curs.getAnDeStudiu();
            int anStudiuStudent=Integer.parseInt(findStudentById(student_id).getAnStudiu());
            if (sem==false && anStudiuStudent==anStudiuCurs)
            {
                System.out.println("salut");
                cursuriNoi.add(curs);
            }
        }

        return CursMapper.mapCursToDetails(cursuriNoi);
    }

    @Transactional
    public void adaugareCursuriInCatalog(AdaugareCursInCatalogDto dto)
    {
        dto.setNota(0);
        catalogService.addCursInCatalog(dto);
    }
}
