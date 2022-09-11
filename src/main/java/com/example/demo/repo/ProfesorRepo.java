package com.example.demo.repo;

import com.example.demo.model.Profesor;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepo extends JpaRepository<Profesor, Long> {
    void deleteProfesorById(Long id);

    Profesor findProfesorById(Long id);

}
