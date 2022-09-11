package com.example.demo.service;

import com.example.demo.dto.ProfesorDto;
import com.example.demo.dto.ProfesorDto1;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Curs;
import com.example.demo.model.Membru;
import com.example.demo.model.Profesor;
import com.example.demo.model.Student;
import com.example.demo.repo.ProfesorRepo;
import com.example.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public interface ProfesorService {
    ProfesorDto addProfesor(ProfesorDto profesorDto);
    List<ProfesorDto> findAllProfesori();
    ProfesorDto updateProfesor(ProfesorDto profesorDto, Long id);
    ProfesorDto updateProfesor1(ProfesorDto1 profesorDto1, Long id);
    ProfesorDto findProfesorById(Long id);
    void deleteProfesor(Long id);
    void trimitereEmail(String toEmail, String body, String subject);
}

