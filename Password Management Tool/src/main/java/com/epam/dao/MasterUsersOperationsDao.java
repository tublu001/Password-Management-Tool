package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.repository.RepositoryDB;
import com.epam.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterUsersOperationsDao
{
    private static final Logger LOGGER = LogManager.getLogger(MasterUsersOperationsDao.class);

    @Autowired
    private RepositoryDB database;

    @Autowired
    private PasswordOperations operate;

    @Autowired
    private Utility utility;

    static List<User> users;


    @Transactional
    public Optional<User> addMasterUser(String userName, String password) throws UserException
    {
        Optional<User> user;
        if (!utility.isValidString(userName) || !utility.isValidString(password))
        {
            throw new UserException("Invalid User Name provided!!!");
        }
        if (isMasterPresent(userName))
        {
            throw new UserException("User already exists in database!!!");
        }
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(operate.encryptPassword(password));
        newUser.getGroups().add("Undefined");
        user = database.setMasterUser(newUser);
        if (user.isEmpty())
        {
            throw new UserException("Error accessing database. Cannot add User to the Database!!!");
        }
        return user;
    }

    @Transactional
    public void showUsers()
    {
        users = database.findAll();
        users.forEach(LOGGER::info);
    }


    @Transactional
    public Optional<User> getUser(String userName)
    {
        users = database.findAll();
        User master = null;
        for (User user : users)
        {
            if (userName.equals(user.getUserName()))
            {
                master = user;
            }
        }
        return Optional.ofNullable(master);
    }

    @Transactional
    public Optional<User> getUser(Long id)
    {
        users = database.findAll();
        User master = null;
        for (User user : users)
        {
            if (id.equals(user.getUserId()))
            {
                master = user;
            }
        }
        return Optional.ofNullable(master);
    }

    @Transactional
    public boolean isMasterPresent(String userName) throws UserException
    {
        if (!utility.isValidString(userName))
        {
            throw new UserException("Invalid User Name provided!!!");
        }
        users = database.findAll();
        boolean isMasterPresent = !(users.stream()
                .filter(user -> userName.equals(user.getUserName()))
                .collect(Collectors.toList())
                .isEmpty());
        return isMasterPresent;
    }
}
