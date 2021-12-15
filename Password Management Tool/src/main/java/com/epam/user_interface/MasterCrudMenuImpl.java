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
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class MasterCrudMenuImpl implements MasterCrudMenu
{
    private static final Logger LOGGER = LogManager.getLogger(MasterCrudMenuImpl.class);

    @Autowired
    private PasswordOperations operate;

    @Autowired
    private UserLoginValidation userLoginValidation;

    @Autowired
    private AccountMenuCRUD accountMenuCRUD;

    @Autowired
    private MasterUsersOperationsDao masterUsersOperationsDao;

    Scanner input = new Scanner(System.in);

    @Override
    public Optional<User> createMaster() throws UserException
    {
        Optional<User> user = Optional.empty();
        LOGGER.info("\n\nCreate a new Master Account\n\nUser Name: ");
        String userName = input.nextLine();
        if (!masterUsersOperationsDao.isMasterPresent(userName))
        {
            LOGGER.info("Enter a New Password: ");
            String password = input.nextLine();
            masterUsersOperationsDao.addMasterUser(userName, password);
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
        masterUsersOperationsDao.showUsers();
    }

    @Override
    public Optional<User> loginMaster() throws UserException
    {
        Optional<User> user = Optional.empty();
        LOGGER.info("\nEnter Your MASTER Account credentials - \n\nUser Name: ");
        String userName = input.nextLine();

        if (!masterUsersOperationsDao.isMasterPresent(userName))
        {
            throw new UserException("User Not Present");
        }
        LOGGER.info("\n\nEnter your (Master) password: ");
        String password = input.nextLine();
        user = userLoginValidation.validateMaster(userName, password);
        if (user.isPresent())
        {
            accountMenuCRUD.showCrudMenu(user.get());
        }
        return user;
    }

}

