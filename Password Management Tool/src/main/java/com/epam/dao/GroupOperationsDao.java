package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GroupOperationsDao
{
    private static final Logger LOGGER = LogManager.getLogger(GroupOperationsDao.class);

    @Autowired
    private RepositoryDB database;

    public boolean isGroupAvailable(User user, String groupName) throws UserException
    {
        return user.getGroups().stream().anyMatch(i -> i.equals(groupName));
    }

    public String addGroupName(User user, String groupName) throws UserException
    {
        String addedGroupName = null;
        if (user.getGroups().add(groupName))
        {
            addedGroupName = groupName;
        } else
        {
            throw new UserException("Error in adding group to the Database!!!");
        }
        return addedGroupName;
    }

    public void showGroups(User user)
    {
        AtomicInteger count = new AtomicInteger();
        user.getGroups().forEach(groupName -> LOGGER.info(count.incrementAndGet() + ". " + groupName));
    }

    public String getGroup(User user, int index) throws UserException
    {
        if (isGroupIndex(user, index))
        {
            return user.getGroups().get(index);
        } else
        {
            throw new UserException("Invalid selection!!! Group not available in this index");
        }
    }

    public boolean updateGroupName(User user, int index, String newGroupName) throws UserException
    {
        boolean groupUpdated = false;
        if (!isGroupIndex(user, index))
        {
            throw new UserException("Invalid Group!!! Group not available in this index");
        } else
        {
            groupUpdated = true;
        }
        user.getGroups().set(index, newGroupName);
        return groupUpdated;
    }

    public boolean updateGroupName(User user, String oldGroupName, String newGroupName) throws UserException
    {
        boolean groupUpdated = false;
        if (!isGroupAvailable(user, oldGroupName))
        {
            throw new UserException("Invalid selection!!! Group not available in database");
        }
        if (isGroupAvailable(user, newGroupName))
        {
            throw new UserException("New group name already exists in database");
        }
        int index = getGroupIndex(user, oldGroupName);
        user.getGroups().set(index, newGroupName);
        updateAccountGroupName(user, oldGroupName, newGroupName);
        database.merge(user);
        groupUpdated = true;

        return groupUpdated;
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

    public void getGroupWiseAccounts(User user)
    {
        LOGGER.info("\n\n|--------------Group Wise All Available Accounts--------------|\n");
        if (!user.equals(null))
        {
            user.getGroups().forEach(groupName ->
            {
                LOGGER.info("\n              " + groupName + "");
                LOGGER.info("--------------------------------");
                getGroupAccounts(user, groupName);
            });
        }
        LOGGER.info("\n");
    }


    void getGroupAccounts(User user, String groupName)
    {
        AtomicInteger count = new AtomicInteger();
        if (!user.equals(null))
        {
            user.getAccounts().forEach(account ->
            {
                if (groupName.equals(account.getAccountGroup()))
                {
                    LOGGER.info(count.incrementAndGet() + ". " + account);
                }
            });
        }
    }

    public boolean isGroupIndex(User user, int index) throws UserException
    {
        if (index > user.getGroups().size() - 1 || index < 0)
        {
            throw new UserException("Invalid selection!!! Group not available in this index");
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
                if (oldGroupName.equals(account.getAccountGroup()))
                {
                    account.setAccountGroup(newGroupName);
                }
            });
        }
    }

    public boolean remove(User user, String groupName) throws UserException
    {
        boolean isDeleted = user.getGroups().remove(groupName);
        return isDeleted;
    }


}
