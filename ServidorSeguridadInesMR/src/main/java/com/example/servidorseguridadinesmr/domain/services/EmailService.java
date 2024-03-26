package com.example.servidorseguridadinesmr.domain.services;

public interface EmailService {

    void sendEmailActivacion(String to, String authCode);

}
