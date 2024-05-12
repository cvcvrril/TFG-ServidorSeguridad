package com.example.servidorseguridadinesmr.data.dao.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.dao.connection.JPAUtil;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.utils.Constantes;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
                    .createNamedQuery(Constantes.NAMED_QUERY_GET_CREDENTIALS_BY_USERNAME)
                    .setParameter(Constantes.USERNAME, username)
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
                    .createNamedQuery(Constantes.NAMED_QUERY_GET_CREDENTIALS_BY_EMAIL)
                    .setParameter(Constantes.EMAIL, email)
                    .getResultList();
            res = Either.right(credentialList.get(0));
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    @Override
    public Either<ErrorSec, CredentialEntity> findByAuthCode(String authCode) {
        Either<ErrorSec, CredentialEntity> res;
        List<CredentialEntity> credentialList;
        em = jpaUtil.getEntityManager();
        try {
            credentialList = em
                    .createNamedQuery(Constantes.NAMED_QUERY_GET_CREDENTIALS_BY_AUTH_CODE)
                    .setParameter(Constantes.AUTH_CODE, authCode)
                    .getResultList();
            res = Either.right(credentialList.get(0));
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    @Override
    public Either<ErrorSec, Integer> update(CredentialEntity credentialUpdated) {
        Either<ErrorSec, Integer> res;
        int conf;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(credentialUpdated);
            tx.commit();
            conf = 1;
            res = Either.right(conf);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        } finally {
            if (em != null) em.close();
        }
        return res;
    }
}
