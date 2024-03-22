package com.example.servidorseguridadinesmr.data.dao.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.dao.connection.JPAUtil;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class DaoCredentialImpl implements DaoCredential {

    private EntityManager em;
    private final JPAUtil jpaUtil;


    public DaoCredentialImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<ErrorSec, CredentialEntity> findByUsername(String username) {
        Either<ErrorSec, CredentialEntity> res;
        List<CredentialEntity> credentialList;
        em = jpaUtil.getEntityManager();
        try {
            credentialList = em
                    .createNamedQuery("GET_CREDENTIALS_BY_USERNAME")
                    .setParameter("username", username)
                    .getResultList();
            res = Either.right(credentialList.get(0));
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    @Override
    public Either<ErrorSec, CredentialEntity> findByEmail(String email) {
        Either<ErrorSec, CredentialEntity> res;
        List<CredentialEntity> credentialList;
        em = jpaUtil.getEntityManager();
        try {
            credentialList = em
                    .createNamedQuery("GET_CREDENTIALS_BY_EMAIL")
                    .setParameter("email", email)
                    .getResultList();
            res = Either.right(credentialList.get(0));
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }
}
