package com.epam.service;

import java.util.Optional;

import com.epam.model.User;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.MySQL_DB;
import com.epam.repository.RepositoryDB;

public class SetPasswordPreference implements UserAccountCrudOperation
{

    RepositoryDB database = new MySQL_DB();

    @Override
    public Optional<User> execute(User user)
    {
        PreferredPassword pp = user.getPrefPass();
        pp.setPrefferdPassword();
        return database.merge(user);
    }

}
