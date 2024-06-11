package com.example.servidorseguridadinesmr.domain.services.impl;


import com.example.servidorseguridadinesmr.domain.model.error.exceptions.EmailException;
import com.example.servidorseguridadinesmr.domain.services.EmailService;
import com.example.servidorseguridadinesmr.utils.Constantes;
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
    private JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmailActivacion(String to, String authCode) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(Constantes.ACTIVACION_DE_LA_CUENTA);
            helper.setText(
                    "<html><body><p>¡Saludos!</p></br><p>Este correo ha sido enviado para que pueda activar su cuenta en el <a href=\"" + Constantes.BASE_URL + ":8081/activation?authCode=" + authCode + "\">siguiente enlace</a></p><p>Si no se ha registrado, ignore el mensaje.</p></body></html>",true
                    );
        }catch (MessagingException e) {
            throw new EmailException(e.getMessage());
        }

    }

    @Override
    public void sendEmailForgotPassword(String to,  String authCode) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(Constantes.RECUPERACION_DE_LA_CUENTA);
            helper.setText(
                    "<html><body><p>¡Saludos!</p></br><p>Al parecer has olvidado tu contraseña para acceder a tu cuenta.</p></br><p>Para cambiarla haga click en el <a href=\"" + Constantes.BASE_URL + ":8081/forgotPassword?authCode=" + authCode + "\">siguiente enlace</a></p><p>Si no quiere cambiar la contraseña, ignore el mensaje.</p></body></html>",true
            );
            emailSender.send(message);
        }catch (MessagingException e) {
            throw new EmailException(e.getMessage());
        }
    }

    @Override
    public void sendEmailBaja(String to, String authCode) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(Constantes.BAJA_DE_LA_CUENTA);
            helper.setText(
                    "<html><body><p>Nos apena ver que has decidido darte de baja.</p></br><p>Pero entendemos que tienes tus motivos.</p></br><p>Si por el contrario crees que ha habido un error haga click en el <a href=\"" + Constantes.BASE_URL + ":8081/darAlta?authCode=" + authCode + "\">siguiente enlace</a> para volver a activar la cuenta.</p><p>¡Gracias por elegir MapEat!</p></body></html>",true
            );
            emailSender.send(message);
        }catch (MessagingException e) {
            throw new EmailException(e.getMessage());
        }
    }


}
