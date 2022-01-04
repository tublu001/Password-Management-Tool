package com.epam.service;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.service.password_operations.PasswordOperations;
import com.epam.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

import static com.epam.utility.Constants.*;

@Service
public class UserLoginValidationImpl implements UserLoginValidation
{
    private static final Logger LOGGER = LogManager.getLogger(UserLoginValidationImpl.class);
    Scanner input = new Scanner(System.in);
    @Autowired
    private PasswordOperations passwordOperations;

    @Autowired
    private MasterUsersOperationsDao masterUsersOperationsDao;

    @Autowired
    private PasswordOperations operate;

    @Autowired
    private Utility utility;

    @Override
    public Optional<User> validateMaster(String userName, String password) throws UserException
    {
        Optional<User> user = Optional.empty();
        if (validateUserName(userName))
        {
            user = masterUsersOperationsDao.getUser(userName);
        }
        if (user.isPresent())
        {
            if (validatePassword(user.get(), password))
            {
                LOGGER.info("\nLogging you in");
            } else
            {
                user = Optional.empty();
            }
        }
        return user;
    }

    @Override
    public boolean validateUserName(String userName) throws UserException
    {
        boolean isUserName = false;
        if (masterUsersOperationsDao.isMasterPresent(userName))
        {
            isUserName = true;
        } else
        {
            throw new UserException(MASTER_NOT_FOUND);
        }
        return isUserName;
    }

    @Override
    public boolean validatePassword(User user, String password) throws UserException
    {
        if (!utility.isValidString(password))
        {
            throw new UserException(INVALID_PASSWORD);
        }
        boolean isPassword = operate.encryptPassword(password).equals(user.getPassword());

        if (!isPassword)
        {
            throw new UserException(INCORRECT_PASSWORD);
        }

        return isPassword;
    }
}
