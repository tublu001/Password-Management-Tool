package com.epam.dao;

import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;

import java.util.Optional;

public interface AccountsControllerDao
{
    boolean store(UserAccountDTO userDetail) throws UserException;

    String retrievePassword(User user, String appName) throws UserException;

    void showAccount(UserAccount account);

    boolean remove(User user, String appName, String masterPassword) throws UserException;

    Optional<UserAccount> getAccountByAppName(User user, String appName) throws UserException;

    boolean isAppName(User user, String appName);

    boolean isAppPresent(User user, String appName) throws UserException;
}
