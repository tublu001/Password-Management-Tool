package com.epam.repository;

import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class MySQL_DB implements RepositoryDB
{
    static EntityManager entityManager;
    static EntityManagerFactory entityManagerFactory;
//    @Autowired
//    EntityManager entityManager;

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
            {
                entityManager.getTransaction().rollback();
            }
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
            {
                entityManager.getTransaction().rollback();
            }
        }
        return Optional.ofNullable(user);
    }
}
