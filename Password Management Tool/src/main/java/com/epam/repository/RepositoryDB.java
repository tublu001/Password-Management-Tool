package com.epam.repository;

import com.epam.model.User;

import java.util.List;
import java.util.Optional;

public interface RepositoryDB
{
    List<User> getMasterUsers();

    Optional<User> setMasterUser(User obj);

    Optional<User> merge(User obj);
}
