package com.example.demo.repo;

import com.example.demo.model.Curs;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {
    void deleteStudentById(Long id);

    Student findStudentById(Long id);

}
