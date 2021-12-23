package com.epam.view;

import com.epam.model.User;
import com.epam.repository.RepositoryDB;
import com.epam.service.password_operations.PreferredPassword;
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
