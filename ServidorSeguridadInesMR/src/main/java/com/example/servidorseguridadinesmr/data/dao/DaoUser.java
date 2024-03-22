package com.example.servidorseguridadinesmr.data.dao;

import com.example.servidorseguridadinesmr.data.model.entities.UserEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

import java.util.List;

/**
 * @author Inés Martínez Rodríguez
 * <br>
 * Interfaz donde se establecen los métodos para el Dao del objeto UserEntity
 * **/

public interface DaoUser {

    Either<ErrorSec, List<UserEntity>> getAll();

    Either<ErrorSec, UserEntity> add(UserEntity nuevoUserEntity);
}
