package com.example.servidorseguridadinesmr.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDTO {

    private String username;
    private String password;
    private String email;
    private Boolean auth;
    private Boolean baja;
    private Boolean pass;
    private String authCode;
}
