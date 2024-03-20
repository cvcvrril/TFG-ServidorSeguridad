package com.example.servidorseguridadinesmr.data.dao.connection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;


/**
 *
 * @author Inés Martínez Rodríguez
 */

@Component
public class JPAUtil {

    private EntityManagerFactory emf;
    public JPAUtil() {
       emf=getEmf();
    }

    private EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory("unit3.hibernate");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
