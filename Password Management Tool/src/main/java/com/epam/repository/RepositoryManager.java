package com.epam.repository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
