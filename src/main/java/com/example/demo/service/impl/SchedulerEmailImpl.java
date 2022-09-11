package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulerEmailImpl {

    @Autowired
    private EmailWithAttachServiceImpl emailWithAttachService;

    @Scheduled(cron="0 0/5 * * * ?" )
    public void cronJobSch() throws MessagingException {
        emailWithAttachService.sendMailWithAttachment("costeapaula151@gmail.com",
                "Salut!",
                "Raport scolar", "" +
                        "C:\\Users\\paula\\Desktop\\Copie copie\\project-costeap-main\\note.xml");
    }
}
