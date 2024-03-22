package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.data.model.entities.RolEntity;
import com.example.servidorseguridadinesmr.data.model.entities.UserEntity;
import com.example.servidorseguridadinesmr.domain.model.UserDTO;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ServiceCredentialImpl implements ServiceCredential {

    private final DaoCredential daoCredential;
    private final DaoUser daoUser;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordHash;

    @Override
    public Either<ErrorSec, CredentialEntity> findByUsername(String username) {
        return daoCredential.findByUsername(username);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = userDetailsService.loadUserByUsername(request.username());
        if (user != null) {
            //var jwtToken = jwtService.generateToken(user.getUsername(), 3000).get();
            //var refreshToken = jwtService.generateRefreshToken(user.getUsername(), 48).get();
            return AuthenticationResponse.builder()
                    //.accessToken(jwtToken)
                    .accessToken("hola")
                    //.refreshToken(refreshToken)
                    .refreshToken("adius")
                    .build();
        } else {
            throw new RuntimeException();
        }


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
