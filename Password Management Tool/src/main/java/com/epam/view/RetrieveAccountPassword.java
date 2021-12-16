package com.epam.view;

import com.epam.dao.AccountsControllerDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class RetrieveAccountPassword implements UserAccountCrudOperation
{
    private static final Logger LOGGER = LogManager.getLogger(RetrieveAccountPassword.class);
    Scanner input = new Scanner(System.in);

    @Autowired
    private AccountsControllerDao accountCredentialOperationsDao;

    @Override
    public Optional<User> execute(User user) throws UserException
    {
        LOGGER.info("\n\nRetrieve Account password\n\nEnter App Name: ");
        String appName = input.nextLine();

        if (accountCredentialOperationsDao.isAppName(user, appName))
        {
            LOGGER.info("Account password is: " + accountCredentialOperationsDao.retrievePassword(user, appName));
        } else
        {
            throw new UserException("App not found...");
        }

        return Optional.of(user);
    }

}
