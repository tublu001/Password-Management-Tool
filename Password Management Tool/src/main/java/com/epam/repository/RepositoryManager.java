package com.epam.repository;

import javax.persistence.EntityManagerFactory;

public class RepositoryManager
{
    private static EntityManagerFactory emf;

    private RepositoryManager()
    {
    }

    public static EntityManagerFactory getEntityManager()
    {
//        if (emf == null)
//            emf = Persistence.createEntityManagerFactory("PMT_DataBase");
        return emf;
    }
}
