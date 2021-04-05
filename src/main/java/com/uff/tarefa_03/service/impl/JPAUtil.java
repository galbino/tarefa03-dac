package com.uff.tarefa_03.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory emf;

    public static EntityManager getEM(){

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("my-pu");
        }
        return emf.createEntityManager();
    }
    public static void fechaEmf(){
        emf.close();
    }
}
