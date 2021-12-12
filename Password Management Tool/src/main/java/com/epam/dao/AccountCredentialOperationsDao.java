package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.model.UserData;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountCredentialOperationsDao implements AccountsControllerDao
{
    public AccountCredentialOperationsDao()
    {
    }

    private static final Logger LOGGER = LogManager.getLogger(AccountCredentialOperationsDao.class);

    @Autowired
    PasswordOperations operate;

    @Autowired
    RepositoryDB database;

    @Override
    public boolean store(UserData userDetail) throws UserException
    {
        User user = userDetail.getUser();
        List<UserAccount> allAccounts = user.getAccounts();
        UserAccount newAccount = new UserAccount();
        boolean isAccountStored;
        newAccount.setAppName(userDetail.getAppName());
        newAccount.setUrl(userDetail.getUrl());
        newAccount.setPassword(userDetail.getPassword());
        newAccount.setAccountGroup(userDetail.getGroupName());
        newAccount.setUser(user);
        allAccounts.add(newAccount);
        Optional<User> returnedUser = database.merge(user);
        if (returnedUser.isEmpty())
            throw new UserException("Account not Added Successfully!!! Error storing to Database");
        else
            isAccountStored = true;
        return isAccountStored;
    }

    @Override
    public String retrievePassword(UserAccount account)
    {
        return operate.decryptPassword(account.getPassword());
    }

    @Override
    public boolean remove(User user, UserAccount account) throws UserException
    {
        boolean isDeleted;
        if (user.getAccounts().remove(account))
        {
            Optional<User> returnedUser = database.merge(user);
            isDeleted = true;
            if (returnedUser.isEmpty())
                throw new UserException("Account cannot be removed!!! Error accessing to Database");
        } else
        {
            throw new UserException("Some Error Occurred");
        }

        return isDeleted;
    }

    @Override
    public boolean isAppName(UserAccount account, String appName)
    {
        return account.getAppName().equals(appName);
    }


    @Override
    public void showAccount(UserAccount account)
    {
        LOGGER.info(account);
    }

    @Override
    public boolean isAppPresent(User user, String appName) throws UserException
    {
        List<UserAccount> matchedAccounts = user.getAccounts().stream().filter(account -> isAppName(account, appName)).collect(Collectors.toList());
        boolean isAppPresent = (!matchedAccounts.isEmpty());
        if (isAppPresent)
            throw new UserException("App already present in Database");
        return isAppPresent;
    }
}
