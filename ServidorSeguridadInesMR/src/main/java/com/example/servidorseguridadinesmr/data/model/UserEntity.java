package com.example.servidorseguridadinesmr.data.model;

/**
 * @author Inés Martínez Rodríguez
 * **/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "autenticado")
    private Boolean auth;
    @OneToOne
    @JoinColumn(name = "id")
    private CredentialEntity credential;
    @ManyToOne
    @JoinColumn(name = "rol")
    private RolEntity rol;
}
