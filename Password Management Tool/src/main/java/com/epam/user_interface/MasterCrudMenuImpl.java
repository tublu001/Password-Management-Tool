package com.epam.user_interface;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.UserLoginValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class MasterCrudMenuImpl implements MasterCrudMenu
{
    private static final Logger LOGGER = LogManager.getLogger(MasterCrudMenuImpl.class);

    @Autowired
    private PasswordOperations operate;

    @Autowired
    private UserLoginValidation userLoginValidation;

    @Autowired
    private AccountMenuCRUD accountMenuCRUD;

    Scanner input = new Scanner(System.in);

    @Override
    public Optional<User> createMaster() throws UserException
    {
        Optional<User> user;
        LOGGER.info("\n\nCreate a new Master Account\n\nUser Name: ");
        String userName = input.nextLine();
        if (!MasterUsersOperationsDao.isMasterPresent(userName))
        {
            LOGGER.info("Enter a New Password: ");
            String password = input.nextLine();
            user = MasterUsersOperationsDao.add(userName, operate.encryptPassword(password));
            LOGGER.info("\nMaster User created");
        } else
        {
            throw new UserException("Failed.. Master already present in database..");
        }
        return user;
    }

    @Override
    public void showAllMasters()
    {
        MasterUsersOperationsDao.showUsers();
    }

    @Override
    public Optional<User> loginMaster() throws UserException
    {
        Optional<User> user = userLoginValidation.validateMaster();

        if (user.isEmpty())
        {
            throw new UserException("User not Found!!!");
        }

        accountMenuCRUD.showCrudMenu(user.get());
        return user;
    }

}

