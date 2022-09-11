package com.example.demo.repo;

import com.example.demo.model.Membru;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findUserByUsername(String username);
    User findByUsername(String username);
    User findUserById(Long id);
}
