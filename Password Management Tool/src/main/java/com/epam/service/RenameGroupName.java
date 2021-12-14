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
public class RenameGroupName implements UserAccountCrudOperation
{
    private static final Logger LOGGER = LogManager.getLogger(RenameGroupName.class);
    Scanner input = new Scanner(System.in);

    @Autowired
    private GroupOperationsDao groupOperations;

    @Autowired
    private RepositoryDB database;

    @Override
    public Optional<User> execute(User user) throws UserException
    {
        LOGGER.info("\n\n|------------Rename Group--------------|\n");
        groupOperations.showGroups(user);
        LOGGER.info("\nChoose any group you want to rename: ");
        int groupNum = input.nextInt();
        input.nextLine();    //consume new line character
        if (groupOperations.isGroupIndex(user, groupNum - 1))
        {
            String oldGroupName = groupOperations.getGroup(user, groupNum - 1);
            LOGGER.info("Give a new Group name: ");
            String newGroupName = input.nextLine();
            try
            {
                groupOperations.updateGroupName(user, oldGroupName, newGroupName);
                database.merge(user);
            } catch (Exception e)
            {
                e.printStackTrace();
            }


            LOGGER.info("Group Name Updated Successfully.. (" + oldGroupName + " ----> " + newGroupName + ")");
        } else
        {
            throw new UserException("Invalid Selection..\n\n");
        }
        return Optional.ofNullable(user);
    }

}
