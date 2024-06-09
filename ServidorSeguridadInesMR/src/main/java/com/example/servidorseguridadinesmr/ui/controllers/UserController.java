package com.example.servidorseguridadinesmr.ui.controllers;

import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.domain.services.ServiceUser;
import com.example.servidorseguridadinesmr.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constantes.USER_PATH)
public class UserController {

    private final ServiceUser serviceUser;

    @GetMapping(Constantes.BY_ID)
    public UserResponse getUserById(@RequestParam(Constantes.ID) int id ) {
        return serviceUser.getUserById(id).get();
    }
}
