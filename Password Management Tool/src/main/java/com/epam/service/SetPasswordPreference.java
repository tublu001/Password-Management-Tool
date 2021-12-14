package com.epam.service;

import com.epam.model.User;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.RepositoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetPasswordPreference implements UserAccountCrudOperation
{
    @Autowired
    private RepositoryDB database;

    @Override
    public Optional<User> execute(User user)
    {
        PreferredPassword pp = user.getPreferredPassword();
        pp.setPreferredPassword();
        return database.merge(user);
    }

}
