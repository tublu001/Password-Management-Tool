package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
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
    private AccountsControllerDao accountsControllerDao;

    public boolean addGroup(User user, String groupName) throws UserException
    {
        if (user.equals(null) || groupName.equals(null) || groupName.equals(""))
        {
            throw new UserException("Give proper Group Name");
        }
        boolean isAdded = user.getGroups().add(groupName);

        return isAdded;
    }

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
}
