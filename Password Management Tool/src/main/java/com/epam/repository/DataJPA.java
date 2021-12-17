package com.epam.repository;

import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
@Primary
public class DataJPA implements RepositoryDB
{
    @Autowired
    private DataJPADB database;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll()
    {
        return (List<User>) database.findAll();
    }

    @Override
    @Transactional
    public Optional<User> setMasterUser(User user)
    {

        User databaseFetchedUser = database.save(user);
        return Optional.ofNullable(databaseFetchedUser);
    }

    @Override
    @Transactional
    public Optional<User> merge(User user)
    {
        return setMasterUser(user);
    }

}
