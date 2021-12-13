package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.MySQL_DB;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterUsersOperationsDao
{
    private static final Logger LOGGER = LogManager.getLogger(MasterUsersOperationsDao.class);

    @Autowired
    RepositoryDB database;

    @Autowired
    PasswordOperations passwordOperations;

    static List<User> users;


    public boolean addMasterUser(String userName, String password) throws UserException
    {
        Optional<User> user;
        if (userName.equals(null) || password.equals(null) || userName.equals("") || password.equals(""))
        {
            throw new UserException("Invalid User Name provided!!!");
        }
        if (isMasterPresent(userName))
        {
            throw new UserException("User already present in Database!!!");
        }
            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setPassword(passwordOperations.encryptPassword(password));
            newUser.getGroups().add("Undefined");
            user = database.setMasterUser(newUser);
            if (user.isEmpty())
            {
                throw new UserException("Some Error occurred... Cannot add User to the Database!!!");
            }

        return true;
    }

    public void showUsers()
    {
        users = database.getMasterUsers();
        users.forEach(LOGGER::info);
    }


    public Optional<User> getUser(String userName)
    {
        users = database.getMasterUsers();
        List<User> matchedUsers = users.stream()
                .filter(user -> userName.equals(user.getUserName()))
                .collect(Collectors.toList());
        return Optional.ofNullable(matchedUsers.get(0));
    }

    public boolean isMasterPresent(String userName) throws UserException
    {
        if (userName.equals(null) || userName.equals(""))
        {
            throw new UserException("Invalid User Name provided!!!");
        }
        users = database.getMasterUsers();
        boolean isMasterPresent = !(users.stream()
                .filter(user -> userName.equals(user.getUserName()))
                .collect(Collectors.toList())
                .isEmpty());
        return isMasterPresent;
    }
}
