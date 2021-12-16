package com.epam.repository;

import com.epam.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryDB
{
    List<User> findAll();

    Optional<User> setMasterUser(User obj);

    Optional<User> merge(User obj);
}
