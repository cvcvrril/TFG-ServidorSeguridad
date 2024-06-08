package com.example.servidorseguridadinesmr.data.model.entities;

/**
 * @author Inés Martínez Rodríguez
 * **/

import com.example.servidorseguridadinesmr.utils.Constantes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constantes.TABLE_ROLES)
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constantes.ID, nullable = false)
    private int id;
    @Column(name = Constantes.ROL_NAME, nullable = false)
    private String rolName;

}
