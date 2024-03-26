package com.example.servidorseguridadinesmr.ui.listeners;

import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ActivationListener {

    private final ServiceCredential serviceCredential;

    @RequestMapping(value="/activation", method= {RequestMethod.GET, RequestMethod.POST})
    public void activation(@RequestParam("authCode")String authCode){
        CredentialEntity credencialAuth = serviceCredential.findByAuthCode(authCode).getOrElseThrow(()-> new RuntimeException());
        if (credencialAuth != null){
            if (credencialAuth.getAuth().equals(true)){
                throw new ValidationException("La cuenta ya ha sido activada. Haga login.");
            }else {
                credencialAuth.setAuth(true);
                serviceCredential.update(credencialAuth);
            }
        }else {
            throw new ValidationException("La credencial no existe");
        }
    }

}
