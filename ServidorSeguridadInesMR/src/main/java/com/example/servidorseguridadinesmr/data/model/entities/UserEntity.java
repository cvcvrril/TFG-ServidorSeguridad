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

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constantes.TABLE_USERS)
@NamedQueries({
        @NamedQuery(name = Constantes.NAMED_QUERY_GET_ALL_USERS, query = QueriesHQL.FROM_USER_ENTITY),
        @NamedQuery(name = Constantes.NAMED_QUERY_GET_ALL_USERS_BY_ID, query = QueriesHQL.FROM_USER_ENTITY_WHERE_ID_ID),
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;
    @Column(name = Constantes.NOMBRE_COMPLETO)
    private String nombreCompleto;
    @Column(name = Constantes.FECHA_NACIMIENTO)
    private LocalDate fechaNacimiento;
    @OneToOne
    @JoinColumn(name = Constantes.CREDENTIAL)
    private CredentialEntity credential;
}
