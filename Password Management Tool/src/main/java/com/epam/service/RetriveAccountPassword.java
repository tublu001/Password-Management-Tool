package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetriveAccountPassword implements UserAccountCrudOperation
{
    private static final Logger LOGGER = LogManager.getLogger(RetriveAccountPassword.class);
    Scanner input = new Scanner(System.in);

    @Override
    public Optional<User> execute(User user) throws UserException
    {
        LOGGER.info("\n\nRetrieve Account password\n\nEnter App Name: ");
        String appName = input.nextLine();
        AccountCredentialOperationsDao accountCredentialOperationsDao = new AccountCredentialOperationsDao();
        boolean isApp = false;

        for (UserAccount account : user.getAccounts())
        {
            if (accountCredentialOperationsDao.isAppName(account, appName))
            {
                isApp = true;
                LOGGER.info("Account password is: " + accountCredentialOperationsDao.retrievePassword(account));
                break;
            }
        }
        if (!isApp)
            throw new UserException("App not found...");

        return Optional.of(user);
    }

}
