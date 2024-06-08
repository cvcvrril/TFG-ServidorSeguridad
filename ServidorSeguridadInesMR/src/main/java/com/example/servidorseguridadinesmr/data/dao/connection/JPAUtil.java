package com.example.servidorseguridadinesmr.data.dao.connection;
import com.example.servidorseguridadinesmr.utils.Constantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


/**
 *
 * @author Inés Martínez Rodríguez
 */

public class JPAUtil {


    private EntityManagerFactory emf;
    public JPAUtil() {
       emf=getEmf();
    }

    private EntityManagerFactory getEmf() {
        return Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
