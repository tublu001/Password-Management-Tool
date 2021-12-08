package com.epam;

import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.dao.UserData;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordOperationsImpl;
import com.epam.service.RetriveAllAccounts;
import com.epam.service.UserAccountCrudOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RetriveAllAccountsTest
{
//    UserAccountCrudOperation operation = new RetriveAllAccounts();
//    AccountCredentialOperationsDao accountOperate = new AccountCredentialOperationsDao();
//    User user,user1;
//
//    @BeforeEach
//    void initiate()
//    {
//        PasswordOperations operate = new PasswordOperationsImpl();
////        MasterUsersOperationsDao.add("Manash", operate.encryptPassword("qwerty"));
////        MasterUsersOperationsDao.add("Suresh", operate.encryptPassword("bfb"));
////        MasterUsersOperationsDao.add("Roshan", operate.encryptPassword("dewdw"));
////        MasterUsersOperationsDao.add("Lokesh", operate.encryptPassword("odhc"));
//
//        user = MasterUsersOperationsDao.getUser("Manash").get();
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
//        Assertions.assertTrue(accountOperate.store(new UserData(user,"a", "url", "password", "Group 1")));
//        Assertions.assertTrue(accountOperate.store(new UserData(user,"b", "url", "password", "Group 1")));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 2"));
//        Assertions.assertTrue(accountOperate.store(new UserData(user,"c", "url", "password", "Group 2")));
//        Assertions.assertTrue(accountOperate.store(new UserData(user,"d", "url", "password", "Group 2")));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 3"));
//        Assertions.assertTrue(accountOperate.store(new UserData(user,"e", "url", "password", "Group 3")));
//        Assertions.assertTrue(accountOperate.store(new UserData(user,"f", "url", "password", "Group 3")));
//
//        user1 = MasterUsersOperationsDao.getUser("Suresh").get();
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user1,"Group 1"));
//        Assertions.assertTrue(accountOperate.store(new UserData(user1,"a", "url", "password", "Group 1")));
//        Assertions.assertTrue(accountOperate.store(new UserData(user1,"b", "url", "password", "Group 1")));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user1,"Group 2"));
//        Assertions.assertTrue(accountOperate.store(new UserData(user1,"c", "url", "password", "Group 2")));
//        Assertions.assertTrue(accountOperate.store(new UserData(user1,"d", "url", "password", "Group 2")));
//        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user1,"Group 3"));
//        Assertions.assertTrue(accountOperate.store(new UserData(user1,"e", "url", "password", "Group 3")));
//        Assertions.assertTrue(accountOperate.store(new UserData(user1,"f", "url", "password", "Group 3")));
//    }
//
//    @Test
//    @DisplayName(value = "retriving all Master User Accounts of correct user")
//    void retriveAllAccountsTest1()
//    {
//        Assertions.assertTrue(operation.execute(user).isPresent());
//    }
//
//    @Test
//    @DisplayName(value = "retriving all Master User Accounts of wrong user")
//    void retriveAllAccountsTest2()
//    {
//        Assertions.assertFalse(operation.execute(null).isPresent());
//    }
}
