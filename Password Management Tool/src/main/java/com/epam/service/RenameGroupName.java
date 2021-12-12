package com.epam.service;

import com.epam.dao.GroupOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class RenameGroupName
{
    private static final Logger LOGGER = LogManager.getLogger(RenameGroupName.class);
    Scanner input = new Scanner(System.in);

    @Autowired
    GroupOperationsDao groupOperations;

    @Autowired
    RepositoryDB database;

    public boolean renameGroup(User user, String oldGroupName, String newGroupName) throws UserException
    {
        groupOperations.showGroups(user);
        if (groupOperations.isGroupAvailable(user, oldGroupName) && !groupOperations.isGroupAvailable(user, newGroupName))
        {
            try
            {
                groupOperations.updateGroupName(user, oldGroupName, newGroupName);
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            groupOperations.updateAccountGroupName(user, oldGroupName, newGroupName);
            database.merge(user);
            LOGGER.info("Group Name Updated Successfully.. (" + oldGroupName + " ----> " + newGroupName + ")");
        } else
        {
            throw new UserException("Invalid Selection..\n\n");
        }
        return true;
    }

}
