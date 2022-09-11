package com.example.demo.service.impl;


import com.example.demo.dto.MembruDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Membru;
import com.example.demo.model.Profesor;
import com.example.demo.model.Student;
import com.example.demo.repo.MembruRepo;
import com.example.demo.service.MembruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class MembruServiceImpl implements MembruService {
    private final MembruRepo membruRepo;

    @Autowired
    public MembruServiceImpl(MembruRepo membruRepo) {
        this.membruRepo = membruRepo;
    }

    public String studentProfesor(MembruDto membru)
    {
        String dtype = "";
        Membru membru1 = membruRepo.findByUsername(membru.getUsername());
        if (membru1 instanceof Student) {
            dtype = "student";
        } else if (membru1 instanceof Profesor) {
            dtype = "profesor";
        }
        return dtype;
    }


    @Transactional
    public Membru findFirstByUsernameAndParola(MembruDto membru) {

        String dtype = "";
        Membru membru1 = membruRepo.findFirstByUsernameAndParola(membru.getUsername(), membru.getParola());
        if (membru1 instanceof Student) {
            dtype = "student";
        } else if (membru1 instanceof Profesor) {
            dtype = "profesor";
        }
        return membruRepo.findFirstByUsernameAndParola(membru.getUsername(), membru.getParola());
    }

    @Transactional
    public List<Membru> findAll(){
        return (List<Membru>) membruRepo.findAll();
    }

    @Transactional
    public Membru findByUsername(String username)
    {
        return membruRepo.findByUsername(username);
    }


}
