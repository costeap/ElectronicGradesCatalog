package com.example.demo.service.impl;

import com.example.demo.dto.ProfesorDto;
import com.example.demo.dto.ProfesorDto1;
import com.example.demo.mapper.CursMapper;
import com.example.demo.mapper.ProfesorMapper;
import com.example.demo.model.Profesor;
import com.example.demo.repo.CursRepo;
import com.example.demo.repo.ProfesorRepo;
import com.example.demo.service.ProfesorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    private final ProfesorRepo profesorRepo;
    private final ModelMapper mapper;
    private final EmailServiceImpl emailService;
    private final EmailWithAttachServiceImpl emailWithAttachService;
    private final CursRepo cursRepo;
    private final BCryptPasswordEncoder passwordEncoder;



    @Autowired
    public ProfesorServiceImpl(ProfesorRepo profesorRepo, ModelMapper mapper, EmailServiceImpl emailService, EmailWithAttachServiceImpl emailWithAttachService, CursRepo cursRepo) {
        this.profesorRepo = profesorRepo;
        this.mapper = mapper;
        this.emailService = emailService;
        this.emailWithAttachService = emailWithAttachService;
        this.cursRepo = cursRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public ProfesorDto addProfesor(ProfesorDto profesorDto) {
        Profesor profesor = ProfesorMapper.mapToEntity(profesorDto);

        String encodePassword=passwordEncoder.encode(profesorDto.getParola());

        profesor.setParola(encodePassword);

        Profesor save = profesorRepo.save(profesor);
        return ProfesorMapper.mapToDTO(save);
    }

    @Transactional
    public List<ProfesorDto> findAllProfesori() {
        List<Profesor> profesori = profesorRepo.findAll();
        return mapToDTOList(profesori);
    }


    @Transactional
    public ProfesorDto updateProfesor(ProfesorDto profesorDto, Long id) {

        Profesor profesor = profesorRepo.findProfesorById(id);
        profesor.setNume(profesorDto.getNume());
        profesor.setEmail(profesorDto.getEmail());
        profesor.setCnp(profesorDto.getCnp());
        profesor.setNumarTel(profesorDto.getNumarTel());
        profesor.setUsername(profesorDto.getUsername());
        profesor.setParola(profesorDto.getParola());
        profesor.setAdmin(profesorDto.getAdmin());
        profesor.setListaCursuri(CursMapper.mapDetailsToCurs(profesorDto.getListaCursuri()));
        Profesor save = profesorRepo.save(profesor);
        return ProfesorMapper.mapToDTO(profesor);
    }

    @Transactional
    public ProfesorDto updateProfesor1(ProfesorDto1 profesorDto, Long id) {

        Profesor profesor = profesorRepo.findProfesorById(id);//.orElseThrow(() -> new UserNotFoundException("Teacher by id " + id + " was not found"));
        profesor.setNume(profesorDto.getNume());
        profesor.setEmail(profesorDto.getEmail());
        profesor.setCnp(profesorDto.getCnp());
        profesor.setNumarTel(profesorDto.getNumarTel());
        profesor.setUsername(profesorDto.getUsername());
        profesor.setParola(profesorDto.getParola());
        profesor.setAdmin(profesorDto.getAdmin());

        profesor.getListaCursuri().add(cursRepo.findCursById(profesorDto.getCurs_id()));

        Profesor save = profesorRepo.save(profesor);
        return ProfesorMapper.mapToDTO(profesor);
    }


    @Transactional
    public ProfesorDto findProfesorById(Long id) {
        Profesor profesor = profesorRepo.findProfesorById(id);
        return ProfesorMapper.mapToDTO(profesor);
    }


    @Transactional
    public void deleteProfesor(Long id) {
        profesorRepo.deleteProfesorById(id);
    }

    @Transactional
    public void trimitereEmail(String toEmail, String body, String subject)
    {
        emailService.sendSimpleEmail(toEmail,body,subject);
    }


    public List<ProfesorDto> mapToDTOList(List<Profesor> profesori) {
        List<ProfesorDto> dtos = profesori.stream().map(profesor -> mapper.map(profesor, ProfesorDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
