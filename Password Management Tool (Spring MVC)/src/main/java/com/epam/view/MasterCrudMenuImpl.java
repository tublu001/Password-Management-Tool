package com.epam.view;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.service.UserLoginValidation;
import com.epam.service.password_operations.PasswordOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

import static com.epam.utility.Constants.DUPLICATE_USER;
import static com.epam.utility.Constants.MASTER_NOT_FOUND;

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
            throw new UserException(DUPLICATE_USER);
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
        Optional<User> user;
        LOGGER.info("\nEnter Your MASTER Account credentials - \n\nUser Name: ");
        String userName = input.nextLine();

        if (!masterUsersOperationsDao.isMasterPresent(userName))
        {
            throw new UserException(MASTER_NOT_FOUND);
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

