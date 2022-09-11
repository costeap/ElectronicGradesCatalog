package com.example.demo.service;

import com.example.demo.dto.MembruDto;
import com.example.demo.model.Membru;
import com.example.demo.repo.MembruRepo;
import com.example.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface MembruService {
    Membru findFirstByUsernameAndParola(MembruDto membru);
    Membru findByUsername(String username);
    String studentProfesor(MembruDto membru);
    List<Membru> findAll();
}
