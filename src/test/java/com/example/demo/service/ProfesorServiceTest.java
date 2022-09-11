package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfesorServiceTest {
    @InjectMocks
    private StudentServiceImpl studentServiceImpl;
    @Mock
    private StudentRepo studentRepo;
    @Mock
    ModelMapper modelMapper;
    @Test
    public void save_givenStudent_expectTheStudent() throws ParseException {
        StudentDto studentDto = StudentDto.builder()
                .nume("Aron Adela")
                .cnp("8764985316489")
                .email("adela@yahoo.com")
                .anStudiu("2")
                .build();

        Student student = Student.builder()
                .nume("Aron Adela")
                .cnp("8764985316489")
                .email("adela@yahoo.com")
                .anStudiu("2")
                .build();

        when(studentRepo.save(any(Student.class))).thenReturn(student);

        StudentDto result = studentServiceImpl.addStudent(studentDto);
        studentDto.setId(result.getId());
        verify(studentRepo).save(any(Student.class));
        assertEquals(studentDto, result);
    }

    @Test
    public void findById_givenId_expectTheStudent() throws ParseException {

        Student student = Student.builder()
                .id((long)2)
                .email("alina@yahoo.com")
                .nume("Maior Alina")
                .cnp("7653429867283")
                .anStudiu("2")
                .build();

        when(studentRepo.findStudentById(anyLong())).thenReturn(student);

        StudentDto fetchedEmployee = studentServiceImpl.findStudentById(student.getId());

        verify(studentRepo).findStudentById(((long) 2));

        assertEquals(fetchedEmployee.getNume(), student.getNume());
        assertEquals(fetchedEmployee.getEmail(), student.getEmail());
        assertEquals((fetchedEmployee.getCnp()), student.getCnp());
        assertEquals(fetchedEmployee.getAnStudiu(), student.getAnStudiu());
    }
    @Test
    public void whenGivenId_shouldDeleteStudent_ifFound(){
        Student student = new Student();
        student.setNume("Pan Andreea");
        student.setCnp("1234567891234");
        student.setAnStudiu("4");
        student.setEmail("andreea@yahoo.com");
        student.setId((long)2);

        studentServiceImpl.deleteStudent(student.getId());
        verify(studentRepo).deleteStudentById(student.getId());
    }


}