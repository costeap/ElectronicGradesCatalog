package com.example.demo.repo;

import com.example.demo.model.Membru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface MembruRepo extends JpaRepository<Membru, Long> {
    Membru findFirstByUsernameAndParola(String username, String parola);
    Membru findByUsername(String username);
}
