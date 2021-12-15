package com.epam.user_interface;

import com.epam.exceptions.UserException;
import com.epam.model.User;

import java.util.Optional;

@FunctionalInterface
public interface UserAccountCrudOperation
{
    Optional<User> execute(User user) throws UserException;
}