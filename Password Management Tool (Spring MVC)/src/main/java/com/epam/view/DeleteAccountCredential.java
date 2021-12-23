package com.epam.view;

import com.epam.dao.AccountsControllerDao;
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
public class DeleteAccountCredential implements UserAccountCrudOperation
{
    private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCredential.class);
    Scanner input = new Scanner(System.in);

    @Autowired
    private RepositoryDB database;

    @Autowired
    private AccountsControllerDao accountCredentialOperationsDao;

    @Override
    public Optional<User> execute(User user) throws UserException
    {
        LOGGER.info("\n\nDelete Account credential\n\nEnter App Name: ");
        String appName = input.nextLine();
        if (!accountCredentialOperationsDao.isAppName(Optional.ofNullable(user), appName))
        {
            throw new UserException("Invalid AppName");
        }
        LOGGER.info("Application Found : " + appName);
        LOGGER.info("\n\nEnter your (Master) password: ");
        String masterPassword = input.nextLine();
        accountCredentialOperationsDao.removeAccount(Optional.ofNullable(user), appName, masterPassword);
        return database.merge(user);
    }

}
