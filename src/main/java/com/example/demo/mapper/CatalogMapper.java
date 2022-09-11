package com.example.demo.mapper;

import com.example.demo.dto.CatalogDto;
import com.example.demo.dto.CatalogDtoVizualizare;
import com.example.demo.dto.CursDto;
import com.example.demo.model.Catalog;
import com.example.demo.model.Curs;

public class CatalogMapper {
    public static CatalogDtoVizualizare mapToDTO(Catalog catalog) {
        return CatalogDtoVizualizare.builder()
                .student_id(catalog.getStudent().getId())
                .numeStudent(catalog.getStudent().getNume())
                .curs_id(catalog.getCurs().getId())
                .numeCurs(catalog.getCurs().getNumeCurs())
                .nota(catalog.getNota())
                .build();
    }

    public static Curs mapToEntity(CursDto catalogDto) {
        return Curs.builder()
                .id(catalogDto.getId())
                .numeCurs(catalogDto.getNumeCurs())
                .anDeStudiu(catalogDto.getAnDeStudiu())
                .listaProfesori(ProfesorMapper.mapDetailsToProfesor(catalogDto.getListaProfesori()))
                .build();
    }
}
