package com.epam.dao;

import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.UserLoginValidation;
import com.epam.repository.RepositoryDB;
import com.epam.utility.Utility;
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
    private RepositoryDB database;

    @Autowired
    private UserLoginValidation userLoginValidation;

    @Autowired
    private GroupOperationsDao groupOperationsDao;

    @Autowired
    private PasswordOperations passwordOperations;

    @Autowired
    private Utility utility;

    @Override
    public boolean store(UserAccountDTO userDetail) throws UserException
    {
        User user = userDetail.getUser();
        if (!utility.isValidString(userDetail.getAppName()))
        {
            throw new UserException("Invalid app name");
        }
        if (!utility.isValidString(userDetail.getUrl()))
        {
            throw new UserException("Invalid URL provided");
        }
        if (!utility.isValidString(userDetail.getPassword()))
        {
            throw new UserException("Invalid password");
        }
        if (!utility.isValidString(userDetail.getGroupName()))
        {
            throw new UserException("Invalid group name");
        }
        if (isAppPresent(user, userDetail.getAppName()))
        {
            throw new UserException("App already present in database...");
        }
        List<UserAccount> allAccounts = user.getAccounts();
        UserAccount newAccount = new UserAccount();
        boolean isAccountStored;
        if (!groupOperationsDao.isGroupAvailable(user, userDetail.getGroupName()))
        {
            user.getGroups().add(userDetail.getGroupName());
        }
        newAccount.setAppName(userDetail.getAppName());
        newAccount.setUrl(userDetail.getUrl());
        newAccount.setPassword(passwordOperations.encryptPassword(userDetail.getPassword()));
        newAccount.setAccountGroup(userDetail.getGroupName());
        newAccount.setUser(user);
        allAccounts.add(newAccount);
        Optional<User> returnedUser = database.merge(user);
        if (returnedUser.isEmpty())
        {
            throw new UserException("Account not Added Successfully!!! Error storing to Database");
        } else
        {
            isAccountStored = true;
        }
        return isAccountStored;
    }

    @Override
    public String retrievePassword(User user, String appName) throws UserException
    {
        String password = "";

        Optional<UserAccount> accountByAppName = getAccountByAppName(user, appName);

        if (accountByAppName.isPresent())
        {
            password = passwordOperations.decryptPassword(accountByAppName.get().getPassword());
        }
        if (!utility.isValidString(password))
        {
            throw new UserException("App not found...");
        }
        return password;
    }

    @Override
    public boolean remove(User user, String appName, String masterPassword) throws UserException
    {
        boolean isDeleted;
        if (!isAppName(user, appName))
        {
            throw new UserException("Invalid AppName");
        }
        if (!userLoginValidation.validatePassword(user, masterPassword))
        {
            throw new UserException("Invalid master password");
        }
        Optional<UserAccount> optionalUserAccount = getAccountByAppName(user, appName);
        UserAccount account = optionalUserAccount.orElseThrow(() -> new UserException("Invalid Account detail"));
        String groupToBeDeleted = account.getAccountGroup();
        if (user.getAccounts().remove(account))
        {
            long numberOfAccountInGroup = user.getAccounts()
                    .stream()
                    .filter(dbAccountGroup -> dbAccountGroup.getAccountGroup().equals(groupToBeDeleted))
                    .count();
            if (numberOfAccountInGroup < 1L)
            {
                groupOperationsDao.remove(user, groupToBeDeleted);
            }
            if (database.merge(user).isEmpty())
            {
                throw new UserException("Account cannot be removed!!! Error accessing to Database");
            }
            isDeleted = true;
        } else
        {
            throw new UserException("Account cannot be removed!!! Error accessing to Database");
        }

        return isDeleted;
    }

    @Override
    public Optional<UserAccount> getAccountByAppName(User user, String appName) throws UserException
    {
        UserAccount account = null;
        for (UserAccount databaseAccount : user.getAccounts())
        {
            if (databaseAccount.getAppName().equals(appName))
            {
                account = databaseAccount;
            }
        }
        if (account == null)
        {
            throw new UserException("App not present");
        }
        return Optional.ofNullable(account);
    }


    @Override
    public boolean isAppName(User user, String appName)
    {
        boolean isAppName = false;
        for (UserAccount account : user.getAccounts())
        {
            if (account.getAppName().equals(appName))
            {
                isAppName = account.getAppName().equals(appName);
            }
        }
        return isAppName;
    }


    @Override
    public void showAccount(UserAccount account)
    {
        LOGGER.info(account);
    }

    @Override
    public boolean isAppPresent(User user, String appName) throws UserException
    {
        List<UserAccount> matchedAccounts = user.getAccounts().stream().filter(account -> isAppName(user, appName)).collect(Collectors.toList());
        return (!matchedAccounts.isEmpty());
    }
}
