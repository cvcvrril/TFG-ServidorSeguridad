package com.example.servidorseguridadinesmr.domain.services;

import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

public interface ServiceCredential {

    AuthenticationResponse authenticate(AuthenticationRequest request);
    Either<ErrorSec, CredentialEntity> findByAuthCode(String authCode);
    Either<ErrorSec, CredentialEntity> findByEmail(String email);
    Either<ErrorSec, Integer> update(CredentialEntity credentialUpdated);
    Either<ErrorSec, Boolean> darBaja(CredentialEntity credential);

}
