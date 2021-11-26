package com.epam;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PwdOperate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MasterUsersOperationsDaoTest
{
    PasswordOperations operate;

    @BeforeEach
    void initiate()
    {
        operate = new PwdOperate();
        MasterUsersOperationsDao.add("Manash", operate.encryptPassword("qwerty"));
        MasterUsersOperationsDao.add("Suresh", operate.encryptPassword("bfb"));
        MasterUsersOperationsDao.add("Roshan", operate.encryptPassword("dewdw"));
        MasterUsersOperationsDao.add("Lokesh", operate.encryptPassword("odhc"));
    }

    @Test
    @DisplayName(value = "adding correct data")
    void addingMasterUsersTest1()
    {
        Assertions.assertTrue(MasterUsersOperationsDao.add("Hello", "qwerty"));
    }

    @Test
    @DisplayName(value = "adding null user name")
    void addingNullMasterUsersTest1()
    {
        Assertions.assertFalse(MasterUsersOperationsDao.add(null, "qwerty"));
    }

    @Test
    @DisplayName(value = "adding null user name")
    void addingNullMasterUsersTest2()
    {
        Assertions.assertFalse(MasterUsersOperationsDao.add(null, operate.encryptPassword("qwerty")));
    }

    @Test
    @DisplayName(value = "adding blank user name")
    void addingBlankMasterUsersTest1()
    {
        Assertions.assertFalse(MasterUsersOperationsDao.add("", "qwerty"));
    }

    @Test
    @DisplayName(value = "adding blank password")
    void addingBlankMasterUsersTest2()
    {
        Assertions.assertFalse(MasterUsersOperationsDao.add("Hello", ""));
    }

    @Test
    @DisplayName(value = "Correct Master present or not")
    void checkingMasterPresenceTest1()
    {
        Assertions.assertTrue(MasterUsersOperationsDao.isMasterPresent("Manash"));
    }

    @Test
    @DisplayName(value = "Wrong Master present or not")
    void checkingMasterPresenceTest2()
    {
        Assertions.assertFalse(MasterUsersOperationsDao.isMasterPresent("Hello"));
    }

    @Test
    @DisplayName(value = "Shows Masterusers")
    void showMasterUsers()
    {
//        Assertions.assertAll(MasterUsersOperationsDao.showUsers());
    }

    @Test
    @DisplayName(value = "Get Correct Master User Object")
    void getMasterUsersTest1()
    {
        Assertions.assertNotNull(MasterUsersOperationsDao.getUser("Manash"));
    }

    @Test
    @DisplayName(value = "Get Wrong Master User Object")
    void getMasterUsersTest2()
    {
        Assertions.assertNull(MasterUsersOperationsDao.getUser("Hello"));
    }

}
