package com.example.servidorseguridadinesmr.domain.services;

import com.example.servidorseguridadinesmr.data.model.entities.UserEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

import java.util.List;

public interface ServiceUser {

    Either<ErrorSec, List<UserEntity>> getAll();

}
