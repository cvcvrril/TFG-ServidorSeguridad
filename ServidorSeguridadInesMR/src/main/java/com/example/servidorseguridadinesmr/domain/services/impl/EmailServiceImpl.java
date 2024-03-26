package com.example.servidorseguridadinesmr.domain.services.impl;


import com.example.servidorseguridadinesmr.domain.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendEmailActivacion(String to, String authCode) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject("Activación de la cuenta");
            helper.setText(
                    "<html><body><p>¡Saludos!</p></br><p>Este correo ha sido enviado para que pueda activar su cuenta en el <a href=\"http://192.168.104.104:8081/activation?authCode=" + authCode + "\">siguiente enlace</a></p><p>Si no se ha registrado, ignore el mensaje.</p></body></html>",true
                    );
            emailSender.send(message);
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }




}
