package com.example.servidorseguridadinesmr.data.dao;


import com.example.servidorseguridadinesmr.data.model.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

public interface DaoCredential {

    Either<ErrorSec, CredentialEntity> findByUsername(String username);

}
