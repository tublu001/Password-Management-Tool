package com.epam.view;

import com.epam.dao.AccountsControllerDao;
import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class AcquireAccountCredentials implements UserAccountCrudOperation
{
    private static final Logger LOGGER = LogManager.getLogger(AcquireAccountCredentials.class);
    Scanner input = new Scanner(System.in);

    @Autowired
    private AccountsControllerDao accountsControllerDao;

    @Autowired
    private PasswordOperations passwordOperations;

    @Autowired
    private GroupMenu groupMenu;

    @Autowired
    private Utility utility;

    @Override
    public Optional<User> execute(User user) throws UserException
    {
        LOGGER.info("\n\nStore Account credentials\n\nEnter App Name: ");
        String appName = input.nextLine();
        if (!utility.isValidString(appName))
        {
            throw new UserException("Invalid App Name");
        }
        if (accountsControllerDao.isAppPresent(user, appName))
        {
            throw new UserException("App already present in Database");
        }
        LOGGER.info("Enter URL: ");
        String url = input.nextLine();
        LOGGER.info("Press enter to generate a new password for (" + appName + ")..  ");
        input.nextLine();

        //Password generation and encryption
        String pwd = passwordOperations.generatePassword(user);

        LOGGER.info("\n\nPassword generated as per your preference. Copy this password and use it in your application:\n" + pwd);
        LOGGER.info("\nPress enter for setting up Group\n");
        input.nextLine();
        String groupName = groupMenu.showGroupUI(user);
        UserAccountDTO userDetail = new UserAccountDTO(user, appName, url, pwd, groupName);
        boolean isStored = accountsControllerDao.storeAccount(userDetail);
        if (!isStored)
        {
            throw new UserException("Error storing in database... Cannot able to store data in Database!!!");
        }
        return Optional.ofNullable(user);
    }

}
