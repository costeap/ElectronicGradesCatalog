package com.example.demo.repo;

import com.example.demo.model.Catalog;
import com.example.demo.model.Curs;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogRepo extends JpaRepository<Catalog, Long> {
    void deleteNotaByStudentAndCurs(Student student, Curs curs);

    Catalog findNotaByStudentAndCurs(Student student, Curs curs);

    List<Catalog> findByCurs(Curs curs);

    List<Catalog> findByStudent(Student student);

    void deleteAllByStudent(Student student);

    void deleteAllByCurs(Curs curs);
}
