package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendSimpleEmail(String toEmail, String body, String subject);
}
