package com.example.servidorseguridadinesmr.data.dao;


import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

public interface DaoCredential {

    Either<ErrorSec, CredentialEntity> findByUsername(String username);
    Either<ErrorSec, CredentialEntity> findByEmail(String email);
    Either<ErrorSec, CredentialEntity> findByAuthCode(String authCode);
    Either<ErrorSec, Integer> update(CredentialEntity credentialUpdated);
}
