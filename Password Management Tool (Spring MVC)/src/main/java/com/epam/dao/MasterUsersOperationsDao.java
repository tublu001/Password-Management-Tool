package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.repository.RepositoryDB;
import com.epam.service.password_operations.PasswordOperations;
import com.epam.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.utility.Constants.*;

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

    private List<User> users;


    @Transactional
    public Optional<User> addMasterUser(String userName, String password) throws UserException
    {
        Optional<User> user;
        if (!utility.isValidString(userName) || !utility.isValidString(password))
        {
            throw new UserException(INVALID_USERNAME);
        }
        if (isMasterPresent(userName))
        {
            throw new UserException(DUPLICATE_USER);
        }
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(operate.encryptPassword(password));
        newUser.getGroups().add("Undefined");
        user = database.setMasterUser(newUser);
        if (user.isEmpty())
        {
            throw new UserException(DB_ERROR);
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
    public Optional<User> getUser(String userName) throws UserException
    {
        User master = null;
        if (isMasterPresent(userName))
        {
            users = database.findAll();
            master = users.stream()
                    .filter(user -> user.getUserName().equals(userName))
                    .collect(Collectors.toList())
                    .get(0);
        }
        return Optional.ofNullable(master);
    }

    @Transactional
    public Optional<User> getUser(Long id)
    {
        users = database.findAll();
        User master = users.stream()
                .filter(user -> user.getUserId().equals(id))
                .collect(Collectors.toList())
                .get(0);
        return Optional.ofNullable(master);
    }

    @Transactional
    public boolean isMasterPresent(String userName) throws UserException
    {
        if (!utility.isValidString(userName))
        {
            throw new UserException(INVALID_USERNAME);
        }
        users = database.findAll();
        boolean isMasterPresent = !(users.stream()
                .filter(user -> userName.equals(user.getUserName()))
                .collect(Collectors.toList())
                .isEmpty());
        return isMasterPresent;
    }
}
