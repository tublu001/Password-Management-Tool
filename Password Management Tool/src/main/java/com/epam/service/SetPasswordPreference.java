package com.epam.service;

import com.epam.model.User;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.RepositoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetPasswordPreference
{
    @Autowired
    private RepositoryDB database;

    public boolean setPreferredPassword(User user, PreferredPassword preferredPasswordObject)
    {
//        PreferredPassword pp = user.getPreferredPassword();
//        pp.setPreferredPasswordByInstance(preferredPasswordObject);
        user.setPreferredPassword(preferredPasswordObject);
        database.merge(user);
        return true;
    }

}
