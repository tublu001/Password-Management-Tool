package com.epam.service;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.GroupOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.UserLoginValidation;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class DeleteAccountCredential
{
    private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCredential.class);

    @Autowired
    RepositoryDB database;

    @Autowired
    AccountsControllerDao accountCredentialOperationsDao;

    @Autowired
    UserLoginValidation userLoginValidation;

    @Autowired
    PasswordOperations passwordOperations;

    @Autowired
    GroupOperationsDao groupOperationsDao;

    public boolean deleteAccount(User user, String appName, String password) throws UserException
    {
        boolean isApp = false;

        for (UserAccount account : user.getAccounts())
        {
            if (accountCredentialOperationsDao.isAppName(account, appName))
            {
                isApp = true;
                LOGGER.info("Application Found : " + appName);
                if (userLoginValidation.validatePassword(user, passwordOperations.decryptPassword(password)))
                {

                    String groupToBeDeleted = account.getAccountGroup();
                    accountCredentialOperationsDao.remove(user, account);

                    long numberOfAccountInGroup = user.getAccounts()
                            .stream()
                            .filter(dbAccountGroup -> dbAccountGroup.getAccountGroup().equals(groupToBeDeleted))
                            .count();
                    if(numberOfAccountInGroup < 1L)
                    {
                        groupOperationsDao.remove(user, groupToBeDeleted);
                    }
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

        return true;
    }

}
