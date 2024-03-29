package com.example.comic_store.controller;

import com.example.comic_store.dto.MailDTO;
import com.example.comic_store.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public String sendMail(@RequestBody MailDTO mailDTO) {
        mailService.sendMail(mailDTO);
        return "Successfully send mail";
    }
}
