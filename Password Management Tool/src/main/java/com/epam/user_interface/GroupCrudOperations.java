package com.epam.user_interface;

import com.epam.dao.GroupOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class GroupCrudOperations implements AccountCrudGroup
{
    private static final Logger LOGGER = LogManager.getLogger(GroupCrudOperations.class);

    public GroupCrudOperations()
    {
    }

    Scanner input = new Scanner(System.in);

    @Autowired
    private GroupOperationsDao groupOperationsDao;

    @Override
    public String createGroup(User user) throws UserException
    {
        LOGGER.info("Enter a new Group Name: ");
        String groupName = input.nextLine();
        if (groupOperationsDao.isGroupAvailable(user, groupName))
        {
            throw new UserException("Group already exists in Database!!!");
        }
        return groupName;
    }

    @Override
    public String storeInExistingGroup(User user) throws UserException, InputMismatchException
    {
        LOGGER.info("\n\nAll the Existing Groups Available: ");
        groupOperationsDao.showGroups(user);
        LOGGER.info("\nSelect any one: ");
        int selectedGroupIndex = input.nextInt();
        String groupName = "Undefined";
        if (groupOperationsDao.isGroupIndex(user, selectedGroupIndex - 1))
        {
            String repositoryGroupName = groupOperationsDao.getGroup(user, selectedGroupIndex - 1);
            if (groupOperationsDao.isGroupAvailable(user, repositoryGroupName))
            {
                groupName = repositoryGroupName;
            }
        }
        return groupName;
    }
}
