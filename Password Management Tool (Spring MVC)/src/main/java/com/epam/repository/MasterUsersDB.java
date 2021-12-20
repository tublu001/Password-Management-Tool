package com.epam.repository;

import com.epam.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MasterUsersDB implements RepositoryDB
{
    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public List<User> findAll()
    {
        return users;
    }

    @Override
    public Optional<User> setMasterUser(User obj)
    {
        return Optional.empty();
    }

    @Override
    public Optional<User> merge(User obj)
    {
        return Optional.empty();
    }
}

