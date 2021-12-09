package com.epam.user_interface;

import com.epam.repository.MySQL_DB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HomeMenu
{
    private static final Logger LOGGER = LogManager.getLogger(HomeMenu.class);

    public HomeMenu()
    {
    }

    @Autowired
    private MasterCrudMenu masterOperations;

    @SuppressWarnings(value = {"all"})
    public void showHomeUI()
    {
        boolean repeatLoop = true;
        final Scanner input = new Scanner(System.in);

        while (repeatLoop)
        {
            MySQL_DB.initialize();
            LOGGER.info("\n\n*************** WELCOME TO PASSWORD MANAGEMENT TOOL ***************\n\n");
            LOGGER.info("1. Log In to your Master Account");
            LOGGER.info("2. Sign Up for a Master Account");
            LOGGER.info("3. Retrive all Master user names");
            LOGGER.info("0. Exit..\n\n\nChoose Any: ");
            String selection = "invalid";

            selection = input.next();


            try
            {
                switch (selection)
                {
                    case "1":
                        masterOperations.loginMaster();
                        break;
                    case "2":
                        masterOperations.createMaster();
                        break;
                    case "3":
                        masterOperations.showAllMasters();
                        break;
                    case "0":
                        repeatLoop = false;
                        LOGGER.info("Thank you... Exiting...");
                        break;
                    default:
                        LOGGER.warn("Invalid Input! Try again...");
                        break;
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
