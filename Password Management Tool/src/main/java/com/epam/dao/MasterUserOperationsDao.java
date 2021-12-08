package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MasterUserOperationsDao
{
    private static final Logger LOGGER = LogManager.getLogger(MasterUserOperationsDao.class);

    public static boolean addGroup(User user, String groupName) throws UserException
    {
        boolean isAdded = false;
        if (user.equals(null) || groupName.equals(null) || groupName.equals(""))
        {
            throw new UserException("Give proper Group Name");
        }
        isAdded = user.getGroups().add(groupName);

        return isAdded;
    }

    public static Optional<User> showAccounts(User user)
    {
        LOGGER.info("\n\nAll Account Details\n\n");
        AtomicInteger sl = new AtomicInteger(1);
        user.getAccounts().forEach(account ->
        {
            LOGGER.info(sl.getAndIncrement() + ". ");
            new AccountCredentialOperationsDao().showAccount(account);
        });
        return Optional.ofNullable(user);
    }
}
