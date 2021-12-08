package com.epam;

import com.epam.dao.*;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordOperationsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountCredentialOperationsDaoTest
{
    //is app present
    //remove
    //add
    AccountCredentialOperationsDao accountOperate = new AccountCredentialOperationsDao();

    @BeforeEach
    void initiate()
    {
//        PasswordOperations operate = new PasswordOperationsImpl();
//        MasterUsersOperationsDao.add("Manash", operate.encryptPassword("qwerty"));
//        MasterUsersOperationsDao.add("Suresh", operate.encryptPassword("bfb"));
//        MasterUsersOperationsDao.add("Roshan", operate.encryptPassword("dewdw"));
//        MasterUsersOperationsDao.add("Lokesh", operate.encryptPassword("odhc"));
    }


    @Test
    @DisplayName(value = "storing a App and validating")
    void storeAppInAccountTest1() throws UserException
    {

        GroupOperationsDao groupOperate = new GroupOperationsDao();
        User user = MasterUsersOperationsDao.getUser("Manash").get();
        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
//        Assertions.assertTrue(accountOperate.store(new UserData(user,"a", "url", "password", "Group 1")));
        Assertions.assertTrue("Group 1".equals(groupOperate.getGroup(user,1)));
//        Assertions.assertTrue(accountOperate.isAppPresent(user,"a"));

    }

    @Test
    @DisplayName(value = "Checking a App is present or not wrong app name provided")
    void checkAppPresentOrNotTest2() throws UserException
    {
        GroupOperationsDao groupOperate = new GroupOperationsDao();
        User user = MasterUsersOperationsDao.getUser("Manash").get();
        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
//        Assertions.assertTrue(accountOperate.store(new UserData(user,"a", "url", "password", "Group 1")));
        Assertions.assertTrue("Group 1".equals(groupOperate.getGroup(user,1)));
//        Assertions.assertFalse(accountOperate.isAppPresent(user,"b"));
    }

//    @Test
//    @DisplayName(value = "update group name of User")
//    void updateGroupNameTest1()
//    {
//        GroupOperationsDao groupOperate = new GroupOperationsDao();
//        MasterUser user = MasterUsersOperationsDao.getUser("Manash");
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 2"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 3"));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 4"));
//        Assertions.assertTrue("Group 2".equals(groupOperate.getGroup(user,2)));
//        Assertions.assertTrue(groupOperate.updateGroupName(user,2, "new group"));
//        Assertions.assertTrue("new group".equals(groupOperate.getGroup(user,2)));
//    }
}
