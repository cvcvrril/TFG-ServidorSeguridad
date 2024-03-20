package com.example.servidorseguridadinesmr.ui.pruebas;

import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.data.dao.connection.JPAUtil;
import com.example.servidorseguridadinesmr.data.dao.impl.DaoUserImpl;
import com.example.servidorseguridadinesmr.domain.services.ServiceUser;
import com.example.servidorseguridadinesmr.domain.services.impl.ServiceUserImpl;

public class MainPrueba {

    public static void main(String[] args) {

        JPAUtil jpaUtil = new JPAUtil();
        DaoUser daoUser = new DaoUserImpl(jpaUtil);
        ServiceUser serviceUser = new ServiceUserImpl(daoUser);
        System.out.println(serviceUser.getAll());
    }

}
