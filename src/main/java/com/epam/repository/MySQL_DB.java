package com.epam.repository;

import java.util.List;

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
//        finally {
//            entityManager.close();
//        }
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

    public boolean persistAndCommit(User obj)
    {
        boolean isSuccess = false;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            isSuccess = true;
        }
        catch (Exception e) {
            if (entityManager != null)
                entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
        return isSuccess;
    }

    public boolean commit(User obj)
    {
        boolean isSuccess = false;
        try {
            entityManager.getTransaction().commit();
            isSuccess = true;
        }
        catch (Exception e) {
            if (entityManager != null)
                entityManager.getTransaction().rollback();
        }
        return isSuccess;
    }

    public boolean merge(User obj)
    {
        boolean isSuccess = false;
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
            isSuccess = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null)
                entityManager.getTransaction().rollback();
        }
//        finally {
//            entityManager.close();
//        }
        return isSuccess;
    }

    public static void initialize()
    {
        entityManagerFactory = RepositoryManager.getEntityManager();
        try {
            entityManager = entityManagerFactory.createEntityManager();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void close()
    {
//        entityManagerFactory.close();
        entityManager = null;
    }
}
