package com.example.servidorseguridadinesmr.data.dao.impl;

import com.example.servidorseguridadinesmr.data.dao.connection.JPAUtil;
import com.example.servidorseguridadinesmr.data.model.entities.UserEntity;
import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class DaoUserImpl implements DaoUser {

    private EntityManager em;
    private final JPAUtil jpaUtil;

    public DaoUserImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<ErrorSec, List<UserEntity>> getAll() {
        Either<ErrorSec, List<UserEntity>> res;
        List<UserEntity> userList;
        em = jpaUtil.getEntityManager();
        try {
            userList = em
                    .createNamedQuery("GET_ALL_USERS", UserEntity.class)
                    .getResultList();
            res = Either.right(userList);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    @Override
    public Either<ErrorSec, UserEntity> add(UserEntity nuevoUserEntity) {

        /**
         * Primero se añade la credencial a base de datos, y luego el usuario ;)
         * **/

        Either<ErrorSec, UserEntity> res;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(nuevoUserEntity.getCredential());
            em.persist(nuevoUserEntity);
            tx.commit();
            res = Either.right(nuevoUserEntity);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            if (tx.isActive()) tx.rollback();
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }finally {
            if (em != null) em.close();
        }

        return res;
    }
}
