package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.epam.utility.constants.*;

@Service
public class GroupOperationsDao
{
    private static final Logger LOGGER = LogManager.getLogger(GroupOperationsDao.class);

    @Autowired
    private RepositoryDB database;

    public boolean isGroupAvailable(Optional<User> user, String groupName)
    {
        return user.get().getGroups().stream().anyMatch(i -> i.equals(groupName));
    }

    public void showGroups(User user)
    {
        AtomicInteger count = new AtomicInteger();
        user.getGroups().forEach(groupName -> LOGGER.info(count.incrementAndGet() + ". " + groupName));
    }

    public String getGroup(Optional<User> user, int index) throws UserException
    {
        user.orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
        if (isGroupIndex(user.get(), index))
        {
            return user.get().getGroups().get(index);
        } else
        {
            throw new UserException(INVALID_SELECTION);
        }
    }

    public boolean updateGroupName(User user, int index, String newGroupName) throws UserException
    {
        if (!isGroupIndex(user, index))
        {
            throw new UserException(INVALID_SELECTION);
        }
        user.getGroups().set(index, newGroupName);
        return true;
    }

    public boolean updateGroupName(User user, String oldGroupName, String newGroupName) throws UserException
    {
        if (!isGroupAvailable(Optional.ofNullable(user), oldGroupName))
        {
            throw new UserException(INVALID_SELECTION);
        }
        if (isGroupAvailable(Optional.ofNullable(user), newGroupName))
        {
            throw new UserException(DUPLICATE_GROUP);
        }
        int index = getGroupIndex(user, oldGroupName);
        user.getGroups().set(index, newGroupName);
        updateAccountGroupName(user, oldGroupName, newGroupName);
        return database.merge(user).isPresent();
    }

    public int getGroupIndex(User user, String groupName)
    {
        int index = 0;
        for (String databaseGroupName : user.getGroups())
        {
            index++;
            if (databaseGroupName.equals(groupName))
            {
                break;
            }
        }
        return index - 1;
    }

    public void getGroupWiseAccounts(User user) throws UserException
    {
        LOGGER.info("\n\n|--------------Group Wise All Available Accounts--------------|\n");
        if (user.equals(null))
        {
            throw new UserException(MASTER_NOT_FOUND);
        }
        user.getGroups().forEach(groupName ->
        {
            LOGGER.info("\n              " + groupName + "");
            LOGGER.info("--------------------------------");
            try
            {
                getGroupAccounts(user, groupName);
            } catch (UserException e)
            {
                e.printStackTrace();
            }
        });
        LOGGER.info("\n");
    }


    void getGroupAccounts(User user, String groupName) throws UserException
    {
        AtomicInteger count = new AtomicInteger();
        if (user.equals(null))
        {
            throw new UserException(MASTER_NOT_FOUND);
        }
        user.getAccounts().forEach(account ->
        {
            if (groupName.equals(account.getGroupName()))
            {
                LOGGER.info(count.incrementAndGet() + ". " + account);
            }
        });
    }

    public boolean isGroupIndex(User user, int index) throws UserException
    {
        if (index > user.getGroups().size() - 1 || index < 0)
        {
            throw new UserException(INVALID_SELECTION);
        } else
        {
            return true;
        }
    }

    public void updateAccountGroupName(User user, String oldGroupName, String newGroupName)
    {
        if (user != null)
        {
            user.getAccounts().forEach(account ->
            {
                if (oldGroupName.equals(account.getGroupName()))
                {
                    account.setGroupName(newGroupName);
                }
            });
        }
    }

    public boolean remove(User user, String groupName) throws UserException
    {
        return user.getGroups().remove(groupName);
    }


}
