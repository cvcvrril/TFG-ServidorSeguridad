package com.example.servidorseguridadinesmr.ui.pruebas.auth;

import com.example.servidorseguridadinesmr.data.model.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final ServiceCredential service;

    @GetMapping("/byUsername")
    public CredentialEntity findByUsername(@RequestParam(name = "username") String username){
        return service.findByUsername(username).get();
    }

}
