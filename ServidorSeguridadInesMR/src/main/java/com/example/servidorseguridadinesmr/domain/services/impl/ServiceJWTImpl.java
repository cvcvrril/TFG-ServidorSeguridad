package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.DatabaseException;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import com.example.servidorseguridadinesmr.domain.services.ServiceJWT;
import com.example.servidorseguridadinesmr.utils.Constantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
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

    private final DaoCredential daoCredential;

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
                .claim(Constantes.ROL, rol)
                .setIssuedAt(now)
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(accessTokenDuration)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getPrivateKey())
                .compact();
    }

    @Override
    public String generateNewAccessToken(String refreshToken) {
        if (refreshToken != null){
            if (checkToken(refreshToken)){
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(getPublicKey())
                        .build()
                        .parseClaimsJws(refreshToken);
                String username = claimsJws.getBody().getSubject();
                CredentialEntity credentialRefresh = daoCredential.findByUsername(username).getOrElseThrow(() -> new DatabaseException("No se encuentra la credencial."));
                return generateAccessToken(credentialRefresh.getUsername(), credentialRefresh.getRol().getRolName());
            }
        }else {
            throw new ValidationException(Constantes.EL_REFRESH_TOKEN_ES_NULO);
        }
        return null;
    }


    private boolean checkToken(String token){
        try {

            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getPublicKey())
                    .build()
                    .parseClaimsJws(token);

            long expirationMillis = claimsJws.getBody().getExpiration().getTime();
            return System.currentTimeMillis() < expirationMillis;

        } catch (ExpiredJwtException e) {
            throw new ValidationException(Constantes.EL_TOKEN_HA_EXPIRADO);
        }
    }

    private PrivateKey getPrivateKey() {
        try {
            KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
            try (InputStream fis = new ClassPathResource(path).getInputStream()) {
                ks.load(fis, password.toCharArray());
            }
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(password.toCharArray());
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(userkeystore, pt);
            if (pkEntry != null) {
                return pkEntry.getPrivateKey();
            } else {
                throw new DatabaseException(Constantes.NO_SE_ENCONTRO_LA_ENTRADA_DE_LA_CLAVE_PRIVADA_EN_LA_KEYSTORE);
            }
        } catch (Exception e) {
            throw new DatabaseException(Constantes.ERROR_AL_CARGAR_LA_CLAVE_PRIVADA_DE_LA_KEYSTORE);
        }
    }

    private PublicKey getPublicKey() {
        try {
            KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
            try (InputStream fis = new ClassPathResource(path).getInputStream()) {
                ks.load(fis, password.toCharArray());
            }
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(password.toCharArray());
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(userkeystore, pt);
            if (pkEntry != null) {
                return pkEntry.getCertificate().getPublicKey();
            } else {
                throw new DatabaseException(Constantes.NO_SE_ENCONTRO_LA_ENTRADA_DE_LA_CLAVE_PUBLICA_EN_LA_KEYSTORE);
            }
        } catch (Exception e) {
            throw new DatabaseException(Constantes.ERROR_AL_CARGAR_LA_CLAVE_PUBLICA_DE_LA_KEYSTORE);
        }
    }
}
