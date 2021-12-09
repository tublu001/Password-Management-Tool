package com.epam.service;

import com.epam.dao.AccountsControllerDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.UserLoginValidation;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class DeleteAccountCredential implements UserAccountCrudOperation
{
    private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCredential.class);
    Scanner input = new Scanner(System.in);

    @Autowired
    RepositoryDB database;

    @Autowired
    AccountsControllerDao accountCredentialOperationsDao;

    @Autowired
    UserLoginValidation userLoginValidation;

    @Override
    public Optional<User> execute(User user) throws UserException
    {


        LOGGER.info("\n\nDelete Account credential\n\nEnter App Name: ");
        String appName = input.nextLine();
        boolean isApp = false;

        for (UserAccount account : user.getAccounts())
        {
            if (accountCredentialOperationsDao.isAppName(account, appName))
            {
                isApp = true;
                LOGGER.info("Application Found : " + appName);
                LOGGER.info("\n\nEnter your (Master) password: ");
                String password = input.nextLine();
                if (userLoginValidation.validatePassword(user, password))
                {
                    accountCredentialOperationsDao.remove(user, account);
                    database.merge(user);
                } else
                {
                    throw new UserException("Incorrect Password...");
                }
                break;
            }
        }
        if (!isApp)
            LOGGER.info("App not found...\n");

        return Optional.ofNullable(user);
    }

}
