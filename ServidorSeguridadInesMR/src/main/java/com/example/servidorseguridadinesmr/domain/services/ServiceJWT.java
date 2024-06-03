package com.example.servidorseguridadinesmr.domain.services;

public interface ServiceJWT {

    String generateRefreshToken(String user);

    String generateAccessToken(String user, String rol);

    String generateNewAccessToken(String refreshToken);

}
