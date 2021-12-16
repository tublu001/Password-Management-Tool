package com.epam.service;

import com.epam.exceptions.UserException;
import com.epam.model.User;

import java.util.Optional;

public interface UserLoginValidation
{
    Optional<User> validateMaster(String userName, String Password) throws UserException;

    boolean validateUserName(String userName) throws UserException;

    boolean validatePassword(User user, String password) throws UserException;
}
