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
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ServiceCredential serviceCredential;
    private final ServiceUser serviceUser;
    private final EmailService emailService;
    private final ServiceJWT jwtService;

    @GetMapping("/login")
    public AuthenticationResponse loginAuth(@RequestParam("username") String username, @RequestParam("password") String password) {
        AuthenticationRequest requestAuth = new AuthenticationRequest(username, password);
        return serviceCredential.authenticate(requestAuth);
    }

    @PostMapping("/registro")
    public UserResponse registroAuth(@RequestBody UserDTO newUser) {
        return serviceUser.registro(newUser).getOrElseThrow( () -> new RuntimeException());
    }

    @PostMapping("/forgotPassword")
    public void forgotPassword(@RequestParam("email") String email){
        CredentialEntity credential = serviceCredential.findByEmail(email).get();
        if (credential == null){
            throw new ValidationException("No se ha encontrado ninguna cuenta con este email.");
        }else {
            emailService.sendEmailForgotPassword(credential.getEmail(), credential.getAuthCode());
        }
    }

    @GetMapping("/refreshToken")
    public String refreshToken(@RequestParam("token") String refreshToken){
        if (refreshToken == null){
            throw new ValidationException("El refreshToken es nulo.");
        }else {
            return jwtService.generateNewAccessToken(refreshToken);
        }
    }
}
