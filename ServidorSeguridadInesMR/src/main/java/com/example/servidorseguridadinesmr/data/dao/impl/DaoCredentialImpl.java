package com.example.servidorseguridadinesmr.data.dao.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.model.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

public class DaoCredentialImpl implements DaoCredential {
    @Override
    public Either<ErrorSec, CredentialEntity> findByUsername(String username) {
        return null;
    }
}
