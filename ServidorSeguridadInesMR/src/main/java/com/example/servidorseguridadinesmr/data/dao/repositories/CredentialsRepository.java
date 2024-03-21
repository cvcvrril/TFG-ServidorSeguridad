package com.example.servidorseguridadinesmr.data.dao.repositories;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.model.CredentialEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialsRepository {

    private final DaoCredential daoCredential;

    public CredentialsRepository(DaoCredential daoCredential) {
        this.daoCredential = daoCredential;
    }

    public CredentialEntity findByUsername(String username) {
        return daoCredential.findByUsername(username).get();
    }

    public CredentialEntity findByEmail(String email) {
        return daoCredential.findByEmail(email).get();
    }
}
