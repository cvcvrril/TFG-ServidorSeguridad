package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceCredentialImpl implements ServiceCredential {

    private final DaoCredential daoCredential;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
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
        if (user != null){
            //var jwtToken = jwtService.generateToken(user.getUsername(), 3000).get();
            //var refreshToken = jwtService.generateRefreshToken(user.getUsername(), 48).get();
            return AuthenticationResponse.builder()
                    //.accessToken(jwtToken)
                    .accessToken("hola")
                    //.refreshToken(refreshToken)
                    .refreshToken("adius")
                    .build();
        }else {
            throw new RuntimeException();
        }


    }


}
