package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void enviarEmail(String receptor, String asunto,String correo){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom("estigo98@gmail.com");
        message.setTo(receptor);
        message.setText(correo);
        message.setSubject(asunto);
        mailSender.send(message);
        System.out.println("Correo enviado con exito");
    }
}
