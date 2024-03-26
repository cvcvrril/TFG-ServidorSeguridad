package com.example.servidorseguridadinesmr.ui.listeners;

import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.services.EmailService;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ActivationListener {

    //private final EmailService emailService;
    private final ServiceCredential serviceCredential;

    @RequestMapping(value="/activation", method= {RequestMethod.GET, RequestMethod.POST})
    public void activation(@RequestParam("authCode")String authCode){
        CredentialEntity credencialAuth = serviceCredential.findByAuthCode(authCode).getOrElseThrow(()-> new RuntimeException());
        if (credencialAuth != null){
            if (credencialAuth.getAuth().equals(true)){
                throw new RuntimeException("La cuenta ya ha sido activada. Haga login.");
            }else {
                credencialAuth.setAuth(true);
                serviceCredential.update(credencialAuth);
            }
        }else {
            throw new RuntimeException("La credencial no existe");
        }
    }

}
