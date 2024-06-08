package com.example.servidorseguridadinesmr.ui.listeners;

import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import com.example.servidorseguridadinesmr.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DarAltaListener {

    private final ServiceCredential serviceCredential;

    @RequestMapping(value= Constantes.DAR_ALTA_PATH, method= {RequestMethod.GET, RequestMethod.POST})
    public void activation(@RequestParam(Constantes.AUTH_CODE)String authCode){
        CredentialEntity credencialAuth = serviceCredential.findByAuthCode(authCode).getOrElseThrow(()-> new RuntimeException());
        if (credencialAuth != null){
            if (credencialAuth.getBaja().equals(false)){
                throw new ValidationException(Constantes.LA_CUENTA_YA_HA_SIDO_DADA_DE_ALTA_HAGA_LOGIN);
            }else {
                credencialAuth.setBaja(false);
                serviceCredential.update(credencialAuth);
            }
        }else {
            throw new ValidationException(Constantes.LA_CREDENCIAL_NO_EXISTE);
        }
    }


}
