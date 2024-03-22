package com.example.servidorseguridadinesmr.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String email;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;

}
