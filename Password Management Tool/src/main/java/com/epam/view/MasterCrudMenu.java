package com.epam.view;

import com.epam.exceptions.UserException;
import com.epam.model.User;

import java.util.Optional;

public interface MasterCrudMenu
{
    Optional<User> createMaster() throws UserException;

    void showAllMasters();

    Optional<User> loginMaster() throws UserException;
}
