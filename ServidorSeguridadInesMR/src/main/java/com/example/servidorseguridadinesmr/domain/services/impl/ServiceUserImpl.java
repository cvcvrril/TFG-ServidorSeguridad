package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.data.model.entities.RolEntity;
import com.example.servidorseguridadinesmr.data.model.entities.UserEntity;
import com.example.servidorseguridadinesmr.domain.model.UserDTO;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.services.EmailService;
import com.example.servidorseguridadinesmr.domain.services.ServiceUser;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUserImpl implements ServiceUser {

    private final DaoUser daoUser;
    private final PasswordEncoder passwordHash;
    private final EmailService emailService;

    @Override
    public Either<ErrorSec, List<UserEntity>> getAll() {
        return daoUser.getAll();
    }

    @Override
    public Either<ErrorSec, UserResponse> registro(UserDTO nuevoUser) {
        Either<ErrorSec, UserResponse> res;
        if (nuevoUser.getUsername() == null && nuevoUser.getPassword() == null && nuevoUser.getEmail() == null) {
            res = Either.left(new ErrorSec());
        } else {
            try {
                String passwordHashed = hashPassword(nuevoUser.getPassword());
                CredentialEntity nuevaCredentialEntity = new CredentialEntity(0, nuevoUser.getUsername(), passwordHashed, nuevoUser.getEmail(), false, new RolEntity(1, "USER"));
                UserEntity nuevoUserEntity = new UserEntity(0,nuevoUser.getNombreCompleto(),nuevoUser.getFechaNacimiento(), nuevaCredentialEntity);
                if (daoUser.add(nuevoUserEntity).isRight()){
                    UserResponse nuevoUserResponse = new UserResponse(nuevoUser.getUsername(), nuevoUser.getEmail(),nuevoUser.getNombreCompleto(), nuevaCredentialEntity.getRol().getRolName());
                    emailService.sendSimpleMessage(nuevoUser.getEmail());
                    res = Either.right(nuevoUserResponse);
                }else {
                    res = Either.left(new ErrorSec(0, "There was an error while saving the new user", LocalDateTime.now()));
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return res;
    }

    private String hashPassword(String password) {
        return passwordHash.encode(password);
    }
}
