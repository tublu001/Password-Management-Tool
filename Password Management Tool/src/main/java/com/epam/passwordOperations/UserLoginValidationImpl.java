package com.epam.passwordOperations;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class UserLoginValidationImpl implements UserLoginValidation
{
    private static final Logger LOGGER = LogManager.getLogger(UserLoginValidationImpl.class);
    Scanner input = new Scanner(System.in);
    @Autowired
    private PasswordOperations passwordOperations;

    @Override
    public Optional<User> validateMaster() throws UserException
    {
        Optional<User> user = Optional.empty();
        LOGGER.info("\nEnter Your MASTER Account credentials - \n\nUser Name: ");
        String userName = input.nextLine();

        if (validateUserName(userName))
        {
            user = MasterUsersOperationsDao.getUser(userName);
        }
        if (user.isPresent())
        {
            LOGGER.info("\n\nEnter your (Master) password: ");
            String password = input.nextLine();
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
        if (MasterUsersOperationsDao.isMasterPresent(userName))
        {
            isUserName = true;
        } else
        {
            throw new UserException("User not present in Database");
        }
        return isUserName;
    }

    @Override
    public boolean validatePassword(User user, String password) throws UserException
    {
        if (password.equals(null) || password.equals(""))
        {
            throw new UserException("Invalid password provided!!!");
        }
        boolean isPassword = passwordOperations.encryptPassword(password).equals(user.getPassword());

        if (!isPassword)
        {
            throw new UserException("Incorrect Password!!!");
        }

        return isPassword;
    }
}
