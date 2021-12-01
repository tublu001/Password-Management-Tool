package com.epam.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositoryManager
{
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PMT_DataBase");
    private static EntityManager repositoryManager = null;
    private RepositoryManager() {}
    public static EntityManager getEntityManager()
    {
        if(repositoryManager == null)
            repositoryManager = emf.createEntityManager();
        return repositoryManager;
    }
}
