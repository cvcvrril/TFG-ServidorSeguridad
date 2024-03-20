package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.data.model.UserEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.services.ServiceUser;
import io.vavr.control.Either;

import java.util.List;

public class ServiceUserImpl implements ServiceUser {

    private final DaoUser daoUser;

    public ServiceUserImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public Either<ErrorSec, List<UserEntity>> getAll() {
        return daoUser.getAll();
    }
}
