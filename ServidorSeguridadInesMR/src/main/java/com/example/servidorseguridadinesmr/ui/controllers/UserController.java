package com.example.servidorseguridadinesmr.ui.controllers;

import com.example.servidorseguridadinesmr.data.model.UserResponse;
import com.example.servidorseguridadinesmr.domain.services.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final ServiceUser serviceUser;

    @GetMapping("/byId")
    public UserResponse loginAuth(@RequestParam("id") int id ) {
        return serviceUser.getUserById(id).get();
    }

}
