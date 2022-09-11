package com.example.demo.mapper;

import com.example.demo.dto.ProfesorDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Profesor;
import com.example.demo.model.Student;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfesorMapper {
    public static List<ProfesorDto> mapProfesorToDetails(List<Profesor> profesori) {
        List<ProfesorDto> dtos=new LinkedList<>();
        for(Profesor profesor:profesori){
            dtos.add(ProfesorDto.builder().id(profesor.getId()).cnp(profesor.getCnp()).nume(profesor.getNume()).email(profesor.getEmail()).numarTel(profesor.getNumarTel()).username(profesor.getUsername()).parola(profesor.getParola()).admin(profesor.getAdmin()).build());
        }
        return dtos;
    }

    public static List<Profesor> mapDetailsToProfesor(List<ProfesorDto> profesoriDto) {
        List<Profesor> profesori=new LinkedList<>();
        for(ProfesorDto profesorDto:profesoriDto){
            profesori.add(Profesor.builder().id(profesorDto.getId()).cnp(profesorDto.getCnp()).nume(profesorDto.getNume()).email(profesorDto.getEmail()).numarTel(profesorDto.getNumarTel()).username(profesorDto.getUsername()).parola(profesorDto.getParola()).admin(profesorDto.getAdmin()).build());
        }
        return profesori;
    }

    public static ProfesorDto mapToDTO(Profesor profesor) {
        return ProfesorDto.builder()
                .id(profesor.getId())
                .nume(profesor.getNume())
                .email(profesor.getEmail())
                .cnp(profesor.getCnp())
                .numarTel(profesor.getNumarTel())
                .username(profesor.getUsername())
                .parola(profesor.getParola())
                .admin(profesor.getAdmin())
                .listaCursuri(CursMapper.mapCursToDetails(profesor.getListaCursuri()))
                .build();
    }

    public static Profesor mapToEntity(ProfesorDto profesorDto) {
        return Profesor.builder()
                .id(profesorDto.getId())
                .nume(profesorDto.getNume())
                .email(profesorDto.getEmail())
                .cnp(profesorDto.getCnp())
                .numarTel(profesorDto.getNumarTel())
                .username(profesorDto.getUsername())
                .parola(profesorDto.getParola())
                .admin(profesorDto.getAdmin())
                .listaCursuri(CursMapper.mapDetailsToCurs(profesorDto.getListaCursuri()))
                .build();
    }

}
