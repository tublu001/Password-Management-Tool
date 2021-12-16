package com.epam.view;

import com.epam.exceptions.UserException;
import com.epam.model.User;

public interface AccountCrudGroup
{
    String createGroup(User user) throws UserException;

    String storeInExistingGroup(User user) throws UserException;
}
