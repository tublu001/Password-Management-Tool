package com.epam.dao;

import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.repository.RepositoryDB;
import com.epam.service.UserLoginValidation;
import com.epam.service.password_operations.PasswordOperations;
import com.epam.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.utility.Constants.*;

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
            throw new UserException(INVALID_APP);
        }
        if (!utility.isValidString(userAccountDTO.getUrl()))
        {
            throw new UserException(INVALID_URL);
        }
        if (!utility.isValidString(userAccountDTO.getPassword()))
        {
            throw new UserException(INVALID_PASSWORD);
        }
        if (!utility.isValidString(userAccountDTO.getGroupName()))
        {
            throw new UserException(INVALID_GROUP);
        }
        if (isAppPresent(Optional.ofNullable(user), userAccountDTO.getAppName()))
        {
            throw new UserException(DUPLICATE_APP);
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
            throw new UserException(DB_ERROR);
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
        user.orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
        Optional<UserAccount> accountByAppName = getAccountByAppName(user, appName);

        if (accountByAppName.isPresent())
        {
            password = passwordOperations.decryptPassword(accountByAppName.get().getPassword());
        }
        if (!utility.isValidString(password))
        {
            throw new UserException(INCORRECT_PASSWORD);
        }
        return password;
    }

    @Override
    public boolean removeAccount(Optional<User> user, String appName, String masterPassword) throws UserException
    {
        boolean isDeleted;
        user.orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
        if (!isAppName(Optional.ofNullable(user.get()), appName))
        {
            throw new UserException(INVALID_APP);
        }
        if (!userLoginValidation.validatePassword(user.get(), masterPassword))
        {
            throw new UserException(INCORRECT_PASSWORD);
        }
        Optional<UserAccount> optionalUserAccount = getAccountByAppName(user, appName);
        UserAccount account = optionalUserAccount.orElseThrow(() -> new UserException(APP_NOT_FOUND));
        String groupToBeDeleted = account.getGroupName();
        if (user.get().getAccounts().remove(account))
        {
            deleteGroupIfContainsNoAccounts(user.get(), groupToBeDeleted);
            if (database.merge(user.get()).isEmpty())
            {
                throw new UserException(DB_ERROR);
            }
            isDeleted = true;
        } else
        {
            throw new UserException(DB_ERROR);
        }

        return isDeleted;
    }

    private void deleteGroupIfContainsNoAccounts(User user, String groupToBeDeleted) throws UserException
    {
        long numberOfAccountInGroup = user.getAccounts()
                .stream()
                .filter(dbAccountGroup -> dbAccountGroup.getGroupName().equals(groupToBeDeleted))
                .count();
        if (numberOfAccountInGroup < 1L && !groupOperationsDao.remove(user, groupToBeDeleted))
        {
            throw new UserException(EMPTY_GROUP);
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
            throw new UserException(APP_NOT_FOUND);
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
            throw new UserException(INVALID_APP);
        }
        if (!utility.isValidString(userAccountDTO.getUrl()))
        {
            throw new UserException(INVALID_URL);
        }
        if (!utility.isValidString(userAccountDTO.getPassword()))
        {
            throw new UserException(INVALID_PASSWORD);
        }
        if (!utility.isValidString(userAccountDTO.getGroupName()))
        {
            throw new UserException(INVALID_GROUP);
        }
        if (!isAppPresent(Optional.ofNullable(user), userAccountDTO.getAppName()))
        {
            throw new UserException(APP_NOT_FOUND);
        }

        UserAccount existingAccount = getAccountByAppName(Optional.ofNullable(user), userAccountDTO.getAppName()).orElseThrow(() -> new UserException(APP_NOT_FOUND));

        String existingGroup = existingAccount.getGroupName();
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
            throw new UserException(DB_ERROR);
        } else
        {
            isAccountUpdated = true;
        }
        return isAccountUpdated;
    }
}
