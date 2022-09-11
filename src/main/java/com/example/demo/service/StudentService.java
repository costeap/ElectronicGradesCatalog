package com.example.demo.service;

import com.example.demo.dto.AdaugareCursInCatalogDto;
import com.example.demo.dto.CursDto;
import com.example.demo.dto.ProfesorDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Curs;
import com.example.demo.model.Profesor;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);
    List<StudentDto> findAllStudenti();
    StudentDto updateStudent(StudentDto student1, Long id);
    StudentDto findStudentById(Long id);
    void deleteStudent(Long id);
    List<CursDto> adaugareCursuri(Long id);
    void adaugareCursuriInCatalog(AdaugareCursInCatalogDto dto);
}

