package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.service.password_operations.PreferredPassword;
import com.epam.repository.RepositoryDB;
import com.epam.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MasterUserOperationsDao
{
    private static final Logger LOGGER = LogManager.getLogger(MasterUserOperationsDao.class);

    @Autowired
    private RepositoryDB database;

    @Autowired
    private AccountsControllerDao accountsControllerDao;

    @Autowired
    private Utility utility;

    public Optional<User> showAccounts(User user)
    {
        LOGGER.info("\n\nAll Account Details\n\n");
        AtomicInteger sl = new AtomicInteger(1);
        user.getAccounts().forEach(account ->
        {
            LOGGER.info(sl.getAndIncrement() + ". ");
            accountsControllerDao.showAccount(account);
        });
        return Optional.ofNullable(user);
    }

    public boolean setPreferredPassword(User user, PreferredPassword preferredPassword) throws UserException
    {
        boolean isSet = false;
        if (user == null)
        {
            throw new UserException("User not present");
        }
        user.setPreferredPassword(preferredPassword);
        isSet = database.merge(user).isPresent();
        return isSet;
    }
}
