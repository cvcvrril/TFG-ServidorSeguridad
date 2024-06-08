package com.example.servidorseguridadinesmr.data.model.entities;

/**
 * @author Inés Martínez Rodríguez
 * **/

import com.example.servidorseguridadinesmr.utils.Constantes;
import com.example.servidorseguridadinesmr.utils.QueriesHQL;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constantes.TABLE_CREDENTIALS)
@NamedQueries({
        @NamedQuery(name = Constantes.GET_CREDENTIALS_BY_USERNAME, query = QueriesHQL.GET_CREDENTIALS_BY_USERNAME),
        @NamedQuery(name = Constantes.GET_CREDENTIALS_BY_EMAIL, query = QueriesHQL.GET_CREDENTIALS_BY_EMAIL),
        @NamedQuery(name = Constantes.GET_CREDENTIALS_BY_AUTH_CODE, query = QueriesHQL.GET_CREDENTIALS_BY_AUTH_CODE)
})
public class CredentialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID)
    private int id;
    @Column(name = Constantes.USERNAME, unique = true, nullable = false)
    private String username;
    @Column(name = Constantes.PASSWORD, nullable = false)
    private String password;
    @Column(name = Constantes.EMAIL, nullable = false)
    private String email;
    @Column(name = Constantes.AUTENTICADO)
    private Boolean auth;
    @Column(name = Constantes.BAJA)
    private Boolean baja;
    @Column(name = Constantes.PASS)
    private Boolean pass;
    @Column(name = Constantes.AUTH_COD)
    private String authCode;
    @ManyToOne
    @JoinColumn(name = Constantes.ROL)
    private RolEntity rol;

}
