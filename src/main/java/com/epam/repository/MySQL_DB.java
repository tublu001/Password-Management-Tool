package com.epam.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.epam.model.User;

public class MySQL_DB implements RepositoryDB
{
    static EntityManager entityManager = RepositoryManager.getEntityManager().createEntityManager();

    @Override
    @SuppressWarnings(value = "Unchecked")
    public List<User> getMasterUsers()
    {
        return entityManager.createQuery("Select usr from User usr").getResultList();
    }

    @Override
    public boolean setMasterUser(User user)
    {
        boolean isSuccess = false;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        isSuccess = true;
        }
        catch (Exception e) {
//            e.printStackTrace();
			if (entityManager != null)
				entityManager.getTransaction().rollback();
			}
        return isSuccess;
    }

    @Override
    public boolean setMasterUsers(List<User> users)
    {
        boolean isSuccess = false;
        users.forEach(user ->
        {
            entityManager.persist(user);
        });
        try {
        entityManager.getTransaction().commit();
            isSuccess = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            if (entityManager != null)
                entityManager.getTransaction().rollback();
        }
        return isSuccess;
    }
}
