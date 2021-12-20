package com.epam.dao;

import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;

import java.util.Optional;

public interface AccountsControllerDao
{
    boolean storeAccount(UserAccountDTO userAccountDTO) throws UserException;

    String retrievePassword(Optional<User> user, String appName) throws UserException;

    void showAccount(UserAccount account);

    boolean remove(Optional<User> user, String appName, String masterPassword) throws UserException;

    Optional<UserAccount> getAccountByAppName(Optional<User> user, String appName) throws UserException;

    boolean isAppName(Optional<User> user, String appName);

    boolean isAppPresent(Optional<User> user, String appName) throws UserException;

    boolean editAccount(UserAccountDTO userAccountDTO) throws UserException;
}
