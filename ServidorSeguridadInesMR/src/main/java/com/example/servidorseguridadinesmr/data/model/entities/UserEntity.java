package com.example.servidorseguridadinesmr.data.model.entities;

/**
 * @author Inés Martínez Rodríguez
 * **/

import com.example.servidorseguridadinesmr.utils.Constantes;
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
        @NamedQuery(name = "GET_ALL_USERS", query = "from UserEntity"),
        @NamedQuery(name = "GET_ALL_USERS_BY_ID", query = "from UserEntity where id = :id"),
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @OneToOne
    @JoinColumn(name = "credential")
    private CredentialEntity credential;
}
