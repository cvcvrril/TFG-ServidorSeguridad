package com.example.servidorseguridadinesmr.data.dao.impl;

import com.example.servidorseguridadinesmr.data.dao.connection.JPAUtil;
import com.example.servidorseguridadinesmr.data.model.UserEntity;
import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;

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
            res = Either.left(new ErrorSec());
        }
        return res;
    }
}
