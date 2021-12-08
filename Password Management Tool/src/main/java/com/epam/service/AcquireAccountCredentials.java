package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.model.UserData;
import com.epam.exceptions.UserException;
import com.epam.user_interface.GroupMenu;
import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordOperationsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AcquireAccountCredentials implements UserAccountCrudOperation
{
    private static final Logger LOGGER = LogManager.getLogger(AcquireAccountCredentials.class);
    Scanner input = new Scanner(System.in);

    @Override
    public Optional<User> execute(User user) throws UserException
    {
        LOGGER.info("\n\nStore Account credentials\n\nEnter App Name: ");
        String appName = input.nextLine();
        if (appName.equals(null) || appName.equals(""))
        {
            throw new UserException("Invalid App Name");
        }
        if (new AccountCredentialOperationsDao().isAppPresent(user, appName))
        {
            throw new UserException("App already present in Database");
        }
        LOGGER.info("Enter URL: ");
        String url = input.nextLine();
        LOGGER.info("Press enter to generate a new password for (" + appName + ")..  ");
        input.nextLine();

        //Password generation and encryption
        PasswordOperations operate = new PasswordOperationsImpl();
        String pwd = operate.generatePassword(user);
        String encPwd = operate.encryptPassword(pwd);

        LOGGER.info("\n\nPassword generated as per your preference. Copy this password and use it in your application:\n" + pwd);
        LOGGER.info("\nPress enter for setting up Group\n");
        input.nextLine();
        String groupName = GroupMenu.showGroupUI(user);
        UserData userDetail = new UserData(user, appName, url, encPwd, groupName);
        boolean isStored = new AccountCredentialOperationsDao().store(userDetail);
        if (!isStored)
            throw new UserException("Something went wrong... Cannot able to store data in Database!!!");
        return Optional.ofNullable(user);
    }

}
