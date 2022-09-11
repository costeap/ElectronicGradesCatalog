package com.example.demo.mapper;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import org.springframework.http.HttpStatus;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StudentMapper {
    public static List<StudentDto> mapStudentToDetails(List<Student> studenti) {
        List<StudentDto> dtos=new LinkedList<>();
        for(Student student:studenti){
            dtos.add(StudentDto.builder().id(student.getId()).cnp(student.getCnp()).nume(student.getNume()).anStudiu(student.getAnStudiu()).email(student.getEmail()).username(student.getUsername()).parola(student.getParola()).build());
        }
        return dtos;
    }

    public static List<Student> mapDetailsToStudent(List<StudentDto> studentiDto) {
        List<Student> studenti=new LinkedList<>();
        for(StudentDto studentDto:studentiDto){
            studenti.add(Student.builder().id(studentDto.getId()).cnp(studentDto.getCnp()).nume(studentDto.getNume()).anStudiu(studentDto.getAnStudiu()).email(studentDto.getEmail()).username(studentDto.getUsername()).parola(studentDto.getParola()).build());
        }
        return studenti;
    }

    public static StudentDto mapToDTO(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .nume(student.getNume())
                .email(student.getEmail())
                .anStudiu(student.getAnStudiu())
                .cnp(student.getCnp())
                .username(student.getUsername())
                .parola(student.getParola())
                .build();
    }

    public static Student mapToEntity(StudentDto studentDto) {
        return Student.builder()
                .id(studentDto.getId())
                .nume(studentDto.getNume())
                .email(studentDto.getEmail())
                .anStudiu(studentDto.getAnStudiu())
                .cnp(studentDto.getCnp())
                .username(studentDto.getUsername())
                .parola(studentDto.getParola())
                .build();
    }
}
