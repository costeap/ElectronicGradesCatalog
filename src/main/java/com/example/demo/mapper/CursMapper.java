package com.example.demo.mapper;

import com.example.demo.dto.CursDto;
import com.example.demo.dto.ProfesorDto;
import com.example.demo.model.Curs;
import com.example.demo.model.Profesor;

import javax.transaction.Transactional;
import java.util.*;

public class CursMapper {
    public static List<CursDto> mapCursToDetails(List<Curs> cursuri) {
        List<CursDto> dtos=new LinkedList<>();

        for(Curs curs:cursuri){
            dtos.add(CursDto.builder().id(curs.getId()).numeCurs(curs.getNumeCurs()).anDeStudiu(curs.getAnDeStudiu()).build());
        }
        return dtos;
    }

    public static List<Curs> mapDetailsToCurs(List<CursDto> cursuriDto) {
        List<Curs> cursuri=new LinkedList<>();
        for(CursDto cursDto:cursuriDto){
            cursuri.add(Curs.builder().id(cursDto.getId()).numeCurs(cursDto.getNumeCurs()).anDeStudiu(cursDto.getAnDeStudiu()).build());
        }
        return cursuri;
    }



    public static CursDto mapToDTO(Curs curs) {
        return CursDto.builder()
                .id(curs.getId())
                .numeCurs(curs.getNumeCurs())
                .anDeStudiu(curs.getAnDeStudiu())
                .listaProfesori(ProfesorMapper.mapProfesorToDetails(curs.getListaProfesori()))
                .build();
    }

    public static Curs mapToEntity(CursDto cursDto) {
        return Curs.builder()
                .id(cursDto.getId())
                .numeCurs(cursDto.getNumeCurs())
                .anDeStudiu(cursDto.getAnDeStudiu())
                .listaProfesori(ProfesorMapper.mapDetailsToProfesor(cursDto.getListaProfesori()))
                .build();
    }
}
