package com.juliana.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {
          @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailVerificacion(String para, String asunto, String mensaje) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(para);           // correo del usuario que se registra
        mailMessage.setSubject(asunto);
        mailMessage.setText(mensaje);
        mailMessage.setFrom("abizhuescas@gmail.com"); // correo de tu app

        mailSender.send(mailMessage);
    }
}
