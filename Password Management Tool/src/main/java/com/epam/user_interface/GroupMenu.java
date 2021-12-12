package com.epam.user_interface;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class GroupMenu
{
    private static final Logger LOGGER = LogManager.getLogger(GroupMenu.class);

    public GroupMenu()
    {
    }

    @Autowired
    private AccountCrudGroup accountCrudGroup;

    @SuppressWarnings(value = {"all"})
    public String showGroupUI(User user)
    {
        boolean repeatLoop = true;
        String groupName = "Undefined";
        final Scanner input = new Scanner(System.in);

        while (repeatLoop)
        {
            LOGGER.info("1. Create a new group");
            LOGGER.info("2. Store in a existing group");
            LOGGER.info("0. Skip..(Ungrouped)\n\n\nChoose Any: ");
            char selection = 'Z';

            selection = input.next().charAt(0);


            try
            {
                switch (selection)
                {
                    case '1':
                        groupName = accountCrudGroup.createGroup(user);
                        repeatLoop = false;
                        break;
                    case '2':
                        groupName = accountCrudGroup.storeInExistingGroup(user);
                        repeatLoop = false;
                        break;
                    case '0':
                        repeatLoop = false;
                        break;
                    default:
                        break;
                }
            } catch (UserException e)
            {
                LOGGER.info(e.getMessage() + "\n\n");
            } catch (InputMismatchException e)
            {
                LOGGER.info("Invalid selection\n\n");
            }
        }
        return groupName;
    }

}
