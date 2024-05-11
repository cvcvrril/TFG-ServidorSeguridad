package com.example.servidorseguridadinesmr.ui.listeners;

import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
public class ForgotPasswordListener {

    private final ServiceCredential serviceCredential;

    @RequestMapping(value="/forgotPassword", method= {RequestMethod.GET, RequestMethod.POST})
    public void changePassword(@RequestParam("authCode") String authCode, @ModelAttribute("newPassword") String newPassword){
        //String newPassword = "";
        CredentialEntity credencialAuth = serviceCredential.findByAuthCode(authCode).getOrElseThrow(()-> new RuntimeException());
        if (credencialAuth != null){
            if (credencialAuth.getPass().equals(true)){
                throw new ValidationException("La contraseña de esta cuenta ya ha sido cambiada. Haga login.");
            }else {
                credencialAuth.setPassword(newPassword);
                serviceCredential.update(credencialAuth);
            }
        }else {
            throw new ValidationException("La credencial no existe");
        }
    }


}
