package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class GroupOperationsDao
{
    private static final Logger LOGGER = LogManager.getLogger(GroupOperationsDao.class);


    public boolean isGroupAvailable(User user, String groupName) throws UserException
    {
        List<String> matchedGroups = user.getGroups().stream().filter(i -> i.equals(groupName)).collect(Collectors.toList());
        return (!matchedGroups.isEmpty());
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

    public boolean updateGroupName(User user, String oldGroupName, String newGroupName) throws UserException
    {
        boolean groupUpdated = false;
        AtomicInteger count = new AtomicInteger();
        count.set(0);
        if (user.equals(null))
        {
            throw new UserException("Invalid selection!!! Group not available in this index");
        } else
        {
            groupUpdated = true;
            int index = 0;
            for(String groupName : user.getGroups())
            {
                index++;
                if (groupName.equals(oldGroupName))
                {
                    break;
                }
            }
            user.getGroups().set(index-1, newGroupName);
        }
        return groupUpdated;
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
            user.getAccounts().forEach(account ->
            {
                if (oldGroupName.equals(account.getAccountGroup()))
                {
                    account.setAccountGroup(newGroupName);
                }
            });
    }


    public boolean remove(User user, String groupName) throws UserException
    {
        boolean isDeleted = false;
        if (user.getGroups().remove(groupName))
        {
            isDeleted = true;
        }
        return isDeleted;
    }


}
