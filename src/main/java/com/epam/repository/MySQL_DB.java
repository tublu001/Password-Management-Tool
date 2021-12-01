package com.epam.repository;

import com.epam.model.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class MySQL_DB implements RepositoryDB
{
    EntityManager entityManager = RepositoryManager.getEntityManager();

    @Override
    public Object getMasterUsers()
    {
        return entityManager.createQuery("select usr from User usr").getResultList();
    }

    @Override
    public void setMasterUsers(Object obj)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }
}
