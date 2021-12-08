package com.epam.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.model.User;

public class MySQL_DB implements RepositoryDB
{
    static EntityManager entityManager;
    static EntityManagerFactory entityManagerFactory;

    @Override
    @SuppressWarnings(value = "Unchecked")
    public List<User> getMasterUsers()
    {
        return entityManager.createQuery("Select usr from User usr").getResultList();
    }

    public static void initialize()
    {
        entityManagerFactory = RepositoryManager.getEntityManager();
        try
        {
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void close()
    {
        entityManager = null;
    }

    @Override
    public Optional<User> setMasterUser(User user)
    {
        try
        {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            if (entityManager != null)
                entityManager.getTransaction().rollback();
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> merge(User user)
    {
        try
        {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            if (entityManager != null)
                entityManager.getTransaction().rollback();
        }
        return Optional.ofNullable(user);
    }
}
