package com.example.servidorseguridadinesmr.domain.services.impl;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.dao.connection.JPAUtil;
import com.example.servidorseguridadinesmr.data.dao.impl.DaoCredentialImpl;
import com.example.servidorseguridadinesmr.data.model.entities.CredentialEntity;
import com.example.servidorseguridadinesmr.data.model.entities.RolEntity;
import com.example.servidorseguridadinesmr.domain.model.CredentialDTO;
import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.services.ServiceCredential;
import com.example.servidorseguridadinesmr.domain.services.ServiceJWT;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ServiceCredentialImplTest {

    @InjectMocks
    private ServiceCredentialImpl serviceCredential;
    @Mock
    private DaoCredential daoCredential;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private ServiceJWT serviceJWT;

    @BeforeEach
    void setUp() {
        serviceCredential = new ServiceCredentialImpl(daoCredential, authenticationManager, serviceJWT);
    }

    /**
     * Test 1 - findByAuthCode
     **/
    @Test
    void findByAuthCode() {
        // Datos simulados
        String authCode = "OKo941Elpp9UVWI_ohbzziAalOvNbuYhlxTDdOlivoA=";
        CredentialEntity mockedCredential = new CredentialEntity(0,"prueba", "prueba", "prueba@gmail.com", true, false,false, "OKo941Elpp9UVWI_ohbzziAalOvNbuYhlxTDdOlivoA=", new RolEntity(1, "USER")); // crea una instancia simulada de CredentialEntity

        // Configurar el comportamiento del DAO
        when(daoCredential.findByAuthCode(authCode)).thenReturn(Either.right(mockedCredential));

        // Llamar al método que estás probando
        Either<ErrorSec, CredentialEntity> result = serviceCredential.findByAuthCode(authCode);

        // Verificar el resultado esperado
        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(mockedCredential);

    }


    /**
     * Test 2 - findByEmail
     **/
    @Test
    void findByEmail() {
        // Datos simulados
        String email = "prueba@gmail.com";
        CredentialEntity mockedCredential = new CredentialEntity(0,"prueba", "prueba", "prueba@gmail.com", true, false,false, "OKo941Elpp9UVWI_ohbzziAalOvNbuYhlxTDdOlivoA=", new RolEntity(1, "USER")); // crea una instancia simulada de CredentialEntity

        // Configurar el comportamiento del DAO
        when(daoCredential.findByEmail(email)).thenReturn(Either.right(mockedCredential));

        // Llamar al método que estás probando
        Either<ErrorSec, CredentialEntity> result = serviceCredential.findByEmail(email);

        // Verificar el resultado esperado
        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(mockedCredential);

    }
}