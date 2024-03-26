package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import com.example.servidorseguridadinesmr.domain.services.ServiceJWT;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceCredentialImpl implements ServiceCredential {

    private final DaoCredential daoCredential;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final ServiceJWT serviceJWT;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = userDetailsService.loadUserByUsername(request.username());
        if (user != null) {
            var jwtToken = serviceJWT.generateAccessToken(user.getUsername(), "Prueba");
            var refreshToken = serviceJWT.generateRefreshToken(user.getUsername());
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    public Either<ErrorSec, CredentialEntity> findByAuthCode(String authCode) {
        return daoCredential.findByAuthCode(authCode);
    }

    @Override
    public Either<ErrorSec, Integer> update(CredentialEntity credentialUpdated) {
        return daoCredential.update(credentialUpdated);
    }

}
