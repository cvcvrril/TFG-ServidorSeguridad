package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.CredentialDTO;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import com.example.servidorseguridadinesmr.domain.services.ServiceJWT;
import com.example.servidorseguridadinesmr.utils.Constantes;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ServiceCredentialImpl implements ServiceCredential {

    private final DaoCredential daoCredential;
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
        CredentialEntity credential = daoCredential.findByUsername(request.username()).getOrElseThrow(()-> new ValidationException("No se ha encontrado una cuenta con ese username"));
        if (credential != null) {
            if (!checkActivation(credential.getUsername())){
                throw new ValidationException(Constantes.DEBE_DE_ACTIVAR_LA_CUENTA);
            }else {
                var jwtToken = serviceJWT.generateAccessToken(credential.getUsername(), credential.getRol().getRolName());
                var refreshToken = serviceJWT.generateRefreshToken(credential.getUsername());
                return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .idUser(credential.getId())
                        .username(credential.getUsername())
                        .email(credential.getEmail())
                        .rol(credential.getRol().getRolName())
                        .build();
            }
        } else {
            throw new ValidationException(Constantes.NO_HAY_CREDENCIALES_CON_ESE_USERNAME);
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

    @Override
    public Either<ErrorSec, Boolean> darBaja(CredentialEntity credential) {
        Either<ErrorSec, Boolean> res = null;
        credential.setBaja(true);
        if (daoCredential.update(credential).isRight()){
            res = Either.right(true);
        }else {
            Either.left(new ErrorSec(0, Constantes.NO_SE_PUDO_DAR_DE_BAJA_AL_USUARIO, LocalDateTime.now()));
        }
        return res;
    }

    private boolean checkActivation(String username){
        boolean res;
        CredentialEntity credential = daoCredential.findByUsername(username).getOrElseThrow(()-> new ValidationException(Constantes.NO_EXISTE_LA_CUENTA_CON_ESE_USERNAME));
        if (credential.getBaja().equals(false)){
            res = credential.getAuth().equals(true);
        }else {
         throw  new ValidationException(Constantes.LA_CUENTA_ESTA_DADA_DE_BAJA);
        }
        return res;
    }

}
