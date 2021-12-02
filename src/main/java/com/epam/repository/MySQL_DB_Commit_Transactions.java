package com.epam.repository;

import com.epam.model.User;

import javax.persistence.EntityManager;

public class MySQL_DB_Commit_Transactions
{
    EntityManager entityManager = RepositoryManager.getEntityManager().createEntityManager();

    public boolean persistAndCommit(Object obj)
    {
        boolean isSuccess = false;
        try {
//            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            isSuccess = true;
        }
        catch (Exception e) {
            if (entityManager != null)
                entityManager.getTransaction().rollback();
        }
        return isSuccess;
    }

    public boolean commit(Object obj)
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
}
