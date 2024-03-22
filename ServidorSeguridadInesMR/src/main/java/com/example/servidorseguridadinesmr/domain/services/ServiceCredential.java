package com.example.servidorseguridadinesmr.domain.services;

import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.domain.model.UserDTO;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import io.vavr.control.Either;

public interface ServiceCredential {

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
