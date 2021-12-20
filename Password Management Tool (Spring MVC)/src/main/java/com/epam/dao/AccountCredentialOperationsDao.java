package com.epam.dao;

import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.service.password_operations.PasswordOperations;
import com.epam.repository.RepositoryDB;
import com.epam.service.UserLoginValidation;
import com.epam.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountCredentialOperationsDao implements AccountsControllerDao
{
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
    public boolean storeAccount(UserAccountDTO userAccountDTO) throws UserException
    {
        User user = userAccountDTO.getUser();
        if (!utility.isValidString(userAccountDTO.getAppName()))
        {
            throw new UserException("Invalid app name");
        }
        if (!utility.isValidString(userAccountDTO.getUrl()))
        {
            throw new UserException("Invalid URL provided");
        }
        if (!utility.isValidString(userAccountDTO.getPassword()))
        {
            throw new UserException("Invalid password");
        }
        if (!utility.isValidString(userAccountDTO.getGroupName()))
        {
            throw new UserException("Invalid group name");
        }
        if (isAppPresent(Optional.ofNullable(user), userAccountDTO.getAppName()))
        {
            throw new UserException("App already present in database...");
        }
        List<UserAccount> allAccounts = user.getAccounts();
        UserAccount newAccount = new UserAccount();
        boolean isAccountStored;

        if (!groupOperationsDao.isGroupAvailable(Optional.ofNullable(user), userAccountDTO.getGroupName()))
        {
            user.getGroups().add(userAccountDTO.getGroupName());
        }
        ModelMapper mapper = new ModelMapper();
        mapper.map(userAccountDTO, newAccount);
        newAccount.setUser(user);
        newAccount.setPassword(passwordOperations.encryptPassword(userAccountDTO.getPassword()));
        allAccounts.add(newAccount);

        Optional<User> databaseFetchedUser = database.merge(user);
        if (databaseFetchedUser.isEmpty())
        {
            throw new UserException("Account not Added Successfully!!! Error storing to Database");
        } else
        {
            isAccountStored = true;
        }
        return isAccountStored;
    }

    @Override
    public String retrievePassword(Optional<User> user, String appName) throws UserException
    {
        String password = "";
        user.orElseThrow(()-> new UserException("User not found"));
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
    public boolean remove(Optional<User> user, String appName, String masterPassword) throws UserException
    {
        boolean isDeleted;
        user.orElseThrow(()-> new UserException("User not present!!!"));
        if (!isAppName(Optional.ofNullable(user.get()), appName))
        {
            throw new UserException("Invalid AppName");
        }
        if (!userLoginValidation.validatePassword(user.get(), masterPassword))
        {
            throw new UserException("Invalid master password");
        }
        Optional<UserAccount> optionalUserAccount = getAccountByAppName(user, appName);
        UserAccount account = optionalUserAccount.orElseThrow(() -> new UserException("Invalid Account detail"));
        String groupToBeDeleted = account.getAccountGroup();
        if (user.get().getAccounts().remove(account))
        {
            deleteGroupIfContainsNoAccounts(user.get(), groupToBeDeleted);
            if (database.merge(user.get()).isEmpty())
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

    private void deleteGroupIfContainsNoAccounts(User user, String groupToBeDeleted) throws UserException
    {
        long numberOfAccountInGroup = user.getAccounts()
                .stream()
                .filter(dbAccountGroup -> dbAccountGroup.getAccountGroup().equals(groupToBeDeleted))
                .count();
        if (numberOfAccountInGroup < 1L && !groupOperationsDao.remove(user, groupToBeDeleted))
        {
            throw new UserException("Group Contains No Accounts!!! Error in deleting");
        }
    }

    @Override
    public Optional<UserAccount> getAccountByAppName(Optional<User> user, String appName) throws UserException
    {
        UserAccount account = null;
        for (UserAccount databaseAccount : user.get().getAccounts())
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
    public boolean isAppName(Optional<User> user, String appName)
    {
        boolean isAppName = false;
        for (UserAccount account : user.get().getAccounts())
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
    public boolean isAppPresent(Optional<User> user, String appName) throws UserException
    {
        List<UserAccount> matchedAccounts = user.get().getAccounts().stream().filter(account -> isAppName(user, appName)).collect(Collectors.toList());
        return (!matchedAccounts.isEmpty());
    }

    @Override
    public boolean editAccount(UserAccountDTO userAccountDTO) throws UserException
    {
        User user = userAccountDTO.getUser();
        if (!utility.isValidString(userAccountDTO.getAppName()))
        {
            throw new UserException("Invalid app name");
        }
        if (!utility.isValidString(userAccountDTO.getUrl()))
        {
            throw new UserException("Invalid URL provided");
        }
        if (!utility.isValidString(userAccountDTO.getPassword()))
        {
            throw new UserException("Invalid password");
        }
        if (!utility.isValidString(userAccountDTO.getGroupName()))
        {
            throw new UserException("Invalid group name");
        }
        if (!isAppPresent(Optional.ofNullable(user), userAccountDTO.getAppName()))
        {
            throw new UserException("App not present in database...");
        }

        UserAccount existingAccount = getAccountByAppName(Optional.ofNullable(user), userAccountDTO.getAppName()).orElseThrow(() -> new UserException("Account not found!!!"));

        String existingGroup = existingAccount.getAccountGroup();
        ModelMapper mapper = new ModelMapper();
        mapper.map(userAccountDTO, existingAccount);
        existingAccount.setUser(user);
        existingAccount.setPassword(passwordOperations.encryptPassword(userAccountDTO.getPassword()));

        boolean isAccountUpdated;

        if (!groupOperationsDao.isGroupAvailable(Optional.ofNullable(user), userAccountDTO.getGroupName()))
        {
            user.getGroups().add(userAccountDTO.getGroupName());
        }

        deleteGroupIfContainsNoAccounts(user, existingGroup);

        Optional<User> databaseFetchedUser = database.merge(user);
        if (databaseFetchedUser.isEmpty())
        {
            throw new UserException("Account not updated Successfully!!! Error storing to Database");
        } else
        {
            isAccountUpdated = true;
        }
        return isAccountUpdated;
    }
}