package com.example.servidorseguridadinesmr.ui.pruebas.controllers;

import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ServiceCredential service;

    @GetMapping("/byUsername")
    public CredentialEntity findByUsername(@RequestParam("username") String username){
        return service.findByUsername(username).get();
    }

    @GetMapping("/login")
    public AuthenticationResponse loginAuth(@RequestParam("username") String username, @RequestParam("password") String password) {
        AuthenticationRequest requestAuth = new AuthenticationRequest(username, password);
        return service.authenticate(requestAuth);
    }

}
