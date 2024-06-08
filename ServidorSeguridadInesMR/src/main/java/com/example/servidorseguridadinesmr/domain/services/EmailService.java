package com.example.servidorseguridadinesmr.domain.services;

public interface EmailService {

    void sendEmailActivacion(String to, String authCode);
    void sendEmailForgotPassword(String to, String authCode);
    void sendEmailBaja(String to, String authCode);

}
