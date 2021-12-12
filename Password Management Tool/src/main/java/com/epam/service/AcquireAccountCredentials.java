package com.epam.service;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserData;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.user_interface.GroupMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class AcquireAccountCredentials
{
    private static final Logger LOGGER = LogManager.getLogger(AcquireAccountCredentials.class);
    Scanner input = new Scanner(System.in);

    @Autowired
    AccountsControllerDao accountsControllerDao;

    @Autowired
    PasswordOperations passwordOperations;

    @Autowired
    MasterUserOperationsDao masterUserOperationsDao;

    public boolean addAccount(UserData userDetail) throws UserException
    {
        User user = userDetail.getUser();
        String appName = userDetail.getAppName();
        String url = userDetail.getUrl();
        String password = userDetail.getPassword();
        String groupName = userDetail.getGroupName();

        if (appName.equals(null) || appName.equals(""))
        {
            throw new UserException("Invalid App Name");
        }
        if (accountsControllerDao.isAppPresent(user, appName))
        {
            throw new UserException("App already present in Database");
        }

        //Password generation and encryption
        String pwd = passwordOperations.generatePassword(user);
        String encPwd = passwordOperations.encryptPassword(pwd);

        LOGGER.info("\n\nPassword generated as per your preference. Copy this password and use it in your application:\n" + pwd);

//        String groupName = groupMenu.showGroupUI(user);
        masterUserOperationsDao.addGroup(user, groupName);
        userDetail.setPassword(encPwd);
        boolean isStored = accountsControllerDao.store(userDetail);
        if (!isStored)
            throw new UserException("Something went wrong... Cannot able to store data in Database!!!");
        return isStored;
    }

}
