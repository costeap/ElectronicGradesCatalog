package com.example.demo.service.impl;

import com.example.demo.dto.CursDto;
import com.example.demo.dto.CursDto1;
import com.example.demo.mapper.CursMapper;
import com.example.demo.mapper.ProfesorMapper;
import com.example.demo.model.Curs;
import com.example.demo.repo.CatalogRepo;
import com.example.demo.repo.CursRepo;
import com.example.demo.repo.ProfesorRepo;
import com.example.demo.service.CursService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursServiceImpl implements CursService {
    private final CursRepo cursRepo;
    private final ModelMapper mapper;
    private final CatalogRepo catalogRepo;
    private final ProfesorRepo profesorRepo;

    @Autowired
    public CursServiceImpl(CursRepo cursRepo, ModelMapper mapper, CatalogRepo catalogRepo, ProfesorRepo profesorRepo) {
        this.cursRepo = cursRepo;
        this.mapper = mapper;
        this.catalogRepo = catalogRepo;
        this.profesorRepo = profesorRepo;
    }

    @Transactional
    public CursDto addCurs(CursDto cursDto) {
        Curs curs = CursMapper.mapToEntity(cursDto);
        Curs save = cursRepo.save(curs);
        return CursMapper.mapToDTO(save);
    }


    @Transactional
    public List<CursDto> findAllCursuri() {
        List<Curs> cursuri = cursRepo.findAll();
        return mapToDTOList(cursuri);
    }


    @Transactional
    public CursDto updateCurs(CursDto cursDto, Long id) {

        Curs curs = cursRepo.findCursById(id);
        curs.setNumeCurs(cursDto.getNumeCurs());
        curs.setAnDeStudiu(cursDto.getAnDeStudiu());
        curs.setListaProfesori(ProfesorMapper.mapDetailsToProfesor(cursDto.getListaProfesori()));
        Curs save = cursRepo.save(curs);
        return CursMapper.mapToDTO(curs);
    }

    @Transactional
    public CursDto updateCurs1(CursDto1 cursDto, Long id) {

        Curs curs = cursRepo.findCursById(id);
        curs.setNumeCurs(cursDto.getNumeCurs());
        curs.setAnDeStudiu(cursDto.getAnDeStudiu());
        curs.getListaProfesori().add(profesorRepo.findProfesorById(cursDto.getProfesor_id()));
        Curs save = cursRepo.save(curs);
        return CursMapper.mapToDTO(curs);
    }


    @Transactional
    public CursDto findCursById(Long id) {
        Curs curs = cursRepo.findCursById(id);
        return CursMapper.mapToDTO(curs);
    }


    @Transactional
    public void deleteCurs(Long id) {
        catalogRepo.deleteAllByCurs(CursMapper.mapToEntity(findCursById(id)));
        cursRepo.deleteCursById(id);
    }


    public List<CursDto> mapToDTOList(List<Curs> cursuri) {
        List<CursDto> dtos = cursuri.stream().map(curs -> mapper.map(curs, CursDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
