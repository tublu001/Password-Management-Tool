package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.repository.MySQL_DB;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MasterUsersOperationsDao
{
    private static final Logger LOGGER = LogManager.getLogger(MasterUsersOperationsDao.class);
    static RepositoryDB database = new MySQL_DB();
    static List<User> users;


    public static Optional<User> add(String userName, String password) throws UserException
    {
        Optional<User> user;
        if (userName.equals(null) || password.equals(null) || userName.equals("") || password.equals(""))
        {
            throw new UserException("Invalid User Name provided!!!");
        }
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.getGroups().add("Undefined");
        user = database.setMasterUser(newUser);
        if (user.isEmpty())
        {
            throw new UserException("Cannot add User to the Database!!! Error accessing database.");
        }
        return user;
    }

    public static void showUsers()
    {
        users = database.getMasterUsers();
        users.forEach(LOGGER::info);
    }


    public static Optional<User> getUser(String userName)
    {
        users = database.getMasterUsers();
        User master = null;

        List<User> matchedUser = users.stream()
                .filter(user -> userName.equals(user.getUserName()))
                .collect(Collectors.toList());
        return Optional.ofNullable(matchedUser.get(0));
    }

    public static boolean isMasterPresent(String userName) throws UserException
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
