package com.example.servidorseguridadinesmr.ui.controllers;

import com.example.servidorseguridadinesmr.data.model.AuthenticationRequest;
import com.example.servidorseguridadinesmr.data.model.AuthenticationResponse;
import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.domain.model.UserDTO;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import com.example.servidorseguridadinesmr.domain.services.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ServiceCredential serviceCredential;
    private final ServiceUser serviceUser;

    @GetMapping("/login")
    public AuthenticationResponse loginAuth(@RequestParam("username") String username, @RequestParam("password") String password) {
        AuthenticationRequest requestAuth = new AuthenticationRequest(username, password);
        return serviceCredential.authenticate(requestAuth);
    }

    @PostMapping("/registro")
    public UserResponse registroAuth(@RequestBody UserDTO newUser) {
        return serviceUser.registro(newUser).getOrElseThrow( () -> new RuntimeException());
    }

}
