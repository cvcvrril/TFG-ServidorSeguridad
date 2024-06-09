package com.example.servidorseguridadinesmr.ui.controllers;

import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.UserDTO;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import com.example.servidorseguridadinesmr.domain.services.EmailService;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import com.example.servidorseguridadinesmr.domain.services.ServiceJWT;
import com.example.servidorseguridadinesmr.domain.services.ServiceUser;
import com.example.servidorseguridadinesmr.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constantes.AUTH_PATH)
public class AuthController {

    private final ServiceCredential serviceCredential;
    private final ServiceUser serviceUser;
    private final EmailService emailService;
    private final ServiceJWT jwtService;

    @GetMapping(Constantes.LOGIN_PATH)
    public AuthenticationResponse loginAuth(@RequestParam(Constantes.USERNAME) String username, @RequestParam(Constantes.PASSWORD) String password) {
        AuthenticationRequest requestAuth = new AuthenticationRequest(username, password);
        return serviceCredential.authenticate(requestAuth);
    }

    @PostMapping(Constantes.REGISTRO_PATH)
    public UserResponse registroAuth(@RequestBody UserDTO newUser) {
        return serviceUser.registro(newUser).getOrElseThrow(() -> new ValidationException(Constantes.HUBO_UN_ERROR));
    }

    @PostMapping(Constantes.FORGOT_PASSWORD_PATH)
    public void forgotPassword(@RequestParam(Constantes.EMAIL) String email) {
        CredentialEntity credential = serviceCredential.findByEmail(email).get();
        if (credential == null) {
            throw new ValidationException(Constantes.NO_SE_HA_ENCONTRADO_NINGUNA_CUENTA_CON_ESTE_EMAIL);
        } else {
            emailService.sendEmailForgotPassword(credential.getEmail(), credential.getAuthCode());
        }
    }

    @GetMapping(Constantes.REFRESH_TOKEN_PATH)
    public String refreshToken(@RequestParam(Constantes.TOKEN) String refreshToken) {
        if (refreshToken == null) {
            throw new ValidationException(Constantes.EL_REFRESH_TOKEN_ES_NULO);
        } else {
            return jwtService.generateNewAccessToken(refreshToken);
        }
    }

    @PostMapping(Constantes.DAR_BAJA_PATH)
    public void darBaja(@RequestParam(Constantes.EMAIL) String email) {
        CredentialEntity credential = serviceCredential.findByEmail(email).get();
        if (credential == null) {
            throw new ValidationException(Constantes.NO_SE_HA_ENCONTRADO_NINGUNA_CUENTA_CON_ESTE_EMAIL);
        }else {
            if (serviceCredential.darBaja(credential).isRight()){
                emailService.sendEmailBaja(credential.getEmail(), credential.getAuthCode());
            }
        }
    }
}
