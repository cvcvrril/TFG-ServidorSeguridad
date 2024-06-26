package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.data.model.entities.RolEntity;
import com.example.servidorseguridadinesmr.data.model.entities.UserEntity;
import com.example.servidorseguridadinesmr.domain.model.UserDTO;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import com.example.servidorseguridadinesmr.domain.services.EmailService;
import com.example.servidorseguridadinesmr.domain.services.ServiceUser;
import com.example.servidorseguridadinesmr.utils.Constantes;
import com.example.servidorseguridadinesmr.utils.RandomBytesGenerator;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ServiceUserImpl implements ServiceUser {

    private final DaoUser daoUser;
    private final PasswordEncoder passwordHash;
    private final EmailService emailService;
    private final RandomBytesGenerator randomBytesGenerator;

    @Override
    public Either<ErrorSec, List<UserEntity>> getAll() {
        return daoUser.getAll();
    }

    @Override
    public Either<ErrorSec, UserResponse> getUserById(int id) {
        return daoUser.getUserById(id);
    }

    @Override
    public Either<ErrorSec, UserResponse> registro(UserDTO nuevoUser) {
        Either<ErrorSec, UserResponse> res;
        if (nuevoUser.getUsername() == null || nuevoUser.getPassword() == null || nuevoUser.getEmail() == null || nuevoUser.getUsername().equals("") || nuevoUser.getPassword().equals("") || nuevoUser.getEmail().equals("")) {
            throw new ValidationException(Constantes.HAY_ALGUNO_DE_LOS_CAMPOS_VACIOS);
        } else {
            try {
                checkEmailRegex(nuevoUser.getEmail());
                String passwordHashed = hashPassword(nuevoUser.getPassword());
                String newAuthCode = randomBytesGenerator.randomBytes();
                CredentialEntity nuevaCredentialEntity = new CredentialEntity(0, nuevoUser.getUsername(), passwordHashed, nuevoUser.getEmail(), false, false, false, newAuthCode, new RolEntity(1, "USER"));
                UserEntity nuevoUserEntity = new UserEntity(0,nuevoUser.getNombreCompleto(),nuevoUser.getFechaNacimiento(), nuevaCredentialEntity);
                if (daoUser.add(nuevoUserEntity).isRight()){
                    UserResponse nuevoUserResponse = new UserResponse(nuevoUser.getUsername(), nuevoUser.getEmail(),nuevoUser.getNombreCompleto(), nuevaCredentialEntity.getRol().getRolName());
                    emailService.sendEmailActivacion(nuevoUser.getEmail(), newAuthCode);
                    res = Either.right(nuevoUserResponse);
                }else {
                    throw new ValidationException(Constantes.HUBO_UN_ERROR_EN_LA_VALIDACION_DE_LOS_DATOS);
                }
            } catch (Exception e) {
                throw new ValidationException(e.getMessage());
            }
        }
        return res;
    }

    private String hashPassword(String password) {
        return passwordHash.encode(password);
    }

    private void checkEmailRegex(String email){
        String emailRegex = Constantes.EMAIL_REGEX;
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new ValidationException(Constantes.EL_EMAIL_INTRODUCIDO_NO_ES_VALIDO);
        }
    }
}
