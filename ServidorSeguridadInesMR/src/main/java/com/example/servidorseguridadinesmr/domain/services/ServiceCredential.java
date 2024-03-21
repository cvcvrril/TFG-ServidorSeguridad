package com.example.servidorseguridadinesmr.domain.services;

import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

public interface ServiceCredential {

    Either<ErrorSec, CredentialEntity> findByUsername(String username);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
