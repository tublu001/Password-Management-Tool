package com.epam.service;

import com.epam.dao.AccountsControllerDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class RetrieveAccountPassword
{
    private static final Logger LOGGER = LogManager.getLogger(RetrieveAccountPassword.class);
    Scanner input = new Scanner(System.in);

    @Autowired
    private AccountsControllerDao accountCredentialOperationsDao;

    public String retrievePassword(User user, String appName) throws UserException
    {
        String password = "";
        for (UserAccount account : user.getAccounts())
        {
            if (accountCredentialOperationsDao.isAppName(account, appName))
            {
                password = accountCredentialOperationsDao.retrievePassword(account);
                LOGGER.info("Account password is: " + password);
                break;
            }
        }
        return password;
    }

}
