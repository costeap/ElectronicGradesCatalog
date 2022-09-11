package com.example.demo.repo;

import com.example.demo.model.Curs;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursRepo extends JpaRepository<Curs, Long> {
    void deleteCursById(Long id);

    Curs findCursById(Long id);

    Curs findCursByNumeCurs(String numeCurs);

    Curs findByNumeCurs(String numeCurs);
}
