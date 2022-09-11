package com.example.demo.service;

import com.example.demo.dto.CursDto;
import com.example.demo.dto.CursDto1;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Curs;
import com.example.demo.model.Student;
import com.example.demo.repo.CursRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public interface CursService {
    CursDto addCurs(CursDto cursDto);
    List<CursDto> findAllCursuri();
    CursDto updateCurs(CursDto cursDto, Long id);
    CursDto updateCurs1(CursDto1 cursDto, Long id);
    CursDto findCursById(Long id);
    void deleteCurs(Long id);
}

