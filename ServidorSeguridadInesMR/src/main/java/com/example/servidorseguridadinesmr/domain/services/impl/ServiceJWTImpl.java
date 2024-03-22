package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.domain.services.ServiceJWT;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ServiceJWTImpl implements ServiceJWT {

    @Value("${application.security.jwt.access-token.expiration}")
    private int accessTokenDuration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private int refreshTokenDuration;
    @Value("${application.security.keystore.path}")
    private String path;
    @Value("${application.security.keystore.password}")
    private String password;
    @Value("${application.security.keystore.userkeystore}")
    private String userkeystore;

    @Override
    public String generateRefreshToken(String user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(now)
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(refreshTokenDuration)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getPrivateKey())
                .compact();
    }

    @Override
    public String generateAccessToken(String user, String rol) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user)
                .claim("rol", rol)
                .setIssuedAt(now)
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(accessTokenDuration)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            try (FileInputStream fis = new FileInputStream(path)) {
                ks.load(fis, password.toCharArray());
            }
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(password.toCharArray());
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(userkeystore, pt);
            if (pkEntry != null) {
                return pkEntry.getPrivateKey();
            } else {
                throw new RuntimeException("No se encontró la entrada de la clave privada en la keystore");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la clave privada de la keystore");
        }
    }
}
