package com.example.servidorseguridadinesmr.ui.listeners;

import com.example.servidorseguridadinesmr.domain.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegistroListener{

    @Autowired
    private final EmailService emailService;

}
