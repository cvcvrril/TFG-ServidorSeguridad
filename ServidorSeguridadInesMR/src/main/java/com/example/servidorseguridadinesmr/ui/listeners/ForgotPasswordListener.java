package com.example.servidorseguridadinesmr.ui.listeners;

import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.DatabaseException;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import com.example.servidorseguridadinesmr.utils.Constantes;
import com.example.servidorseguridadinesmr.utils.RandomBytesGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ForgotPasswordListener {

    private final ServiceCredential serviceCredential;
    private final RandomBytesGenerator randomBytesGenerator;
    private final PasswordEncoder passwordHash;

    @RequestMapping(value= Constantes.FORGOT_PASSWORD_LIS_PATH, method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> changePassword(@RequestParam(Constantes.AUTH_CODE) String authCode){
        CredentialEntity credencialAuth = serviceCredential.findByAuthCode(authCode).getOrElseThrow(()-> new DatabaseException(Constantes.NO_SE_HA_ENCONTRADO_LA_CREDENCIAL));
        if (credencialAuth != null){
            String newPassword = randomBytesGenerator.randomNewPassword();
            if (credencialAuth.getPass().equals(true)){
                throw new ValidationException(Constantes.LA_CONTRASENA_DE_ESTA_CUENTA_YA_HA_SIDO_CAMBIADA_HAGA_LOGIN);
            }else {
                credencialAuth.setPass(true);
                String passwordHashed = passwordHash.encode(newPassword);
                credencialAuth.setPassword(passwordHashed);
                serviceCredential.update(credencialAuth);
                return new ResponseEntity<>(newPassword, HttpStatus.OK);
            }
        }else {
            throw new ValidationException(Constantes.LA_CREDENCIAL_NO_EXISTE);
        }
    }
}
