package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
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
    //private final UserDetailsService userDetailsService;
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
        //var user = userDetailsService.loadUserByUsername(request.username());
        CredentialEntity credential = daoCredential.findByUsername(request.username()).getOrElseThrow(()-> new ValidationException("No se ha encontrado una cuenta con ese username"));
        if (credential != null) {
            if (!checkActivation(credential.getUsername())){
                throw new ValidationException("Debe de activar la cuenta");
            }else {
                var jwtToken = serviceJWT.generateAccessToken(credential.getUsername(), credential.getRol().getRolName());
                var refreshToken = serviceJWT.generateRefreshToken(credential.getUsername());
                return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .idUser(String.valueOf(credential.getId()))
                        .build();
            }
        } else {
            throw new ValidationException("No hay credenciales con ese username");
        }

    }

    @Override
    public Either<ErrorSec, CredentialEntity> findByAuthCode(String authCode) {
        return daoCredential.findByAuthCode(authCode);
    }

    @Override
    public Either<ErrorSec, CredentialEntity> findByEmail(String email) {
        return daoCredential.findByEmail(email);
    }

    @Override
    public Either<ErrorSec, Integer> update(CredentialEntity credentialUpdated) {
        return daoCredential.update(credentialUpdated);
    }

    private boolean checkActivation(String username){
        boolean res;
        CredentialEntity credential = daoCredential.findByUsername(username).getOrElseThrow(()-> new ValidationException("No existe la cuenta con ese username"));
        if (credential.getBaja().equals(false)){
            res = credential.getAuth().equals(true);
        }else {
         throw  new ValidationException("La cuenta está dada de baja");
        }
        return res;
    }

}
