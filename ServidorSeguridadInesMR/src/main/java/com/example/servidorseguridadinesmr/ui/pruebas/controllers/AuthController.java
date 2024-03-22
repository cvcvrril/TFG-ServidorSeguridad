package com.example.servidorseguridadinesmr.ui.pruebas.controllers;

import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.UserDTO;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/registro")
    public UserResponse registroAuth(@RequestBody UserDTO newUser) {
        return service.registro(newUser).getOrElseThrow( () -> new RuntimeException());
    }

}
