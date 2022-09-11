package com.example.demo.service;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailWithAttachService {
    public void sendMailWithAttachment(String toEmail,
                                       String body,
                                       String subject,
                                       String attachment) throws MessagingException;
}
