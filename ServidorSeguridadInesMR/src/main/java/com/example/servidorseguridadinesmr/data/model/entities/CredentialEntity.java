package com.example.servidorseguridadinesmr.data.model.entities;

/**
 * @author Inés Martínez Rodríguez
 * **/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credentials")
@NamedQueries({
        @NamedQuery(name = "GET_CREDENTIALS_BY_USERNAME", query = "from CredentialEntity where username = :username"),
        @NamedQuery(name = "GET_CREDENTIALS_BY_EMAIL", query = "from CredentialEntity where email = :email"),
        @NamedQuery(name = "GET_CREDENTIALS_BY_AUTH_CODE", query = "from CredentialEntity where authCode = :authCode")
})
public class CredentialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "autenticado")
    private Boolean auth;
    @Column(name = "baja")
    private Boolean baja;
    @Column(name = "pass")
    private Boolean pass;
    @Column(name = "auth_code")
    private String authCode;
    @ManyToOne
    @JoinColumn(name = "rol")
    private RolEntity rol;

}
