package com.epam;

import com.epam.dao.GroupOperationsDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordOperationsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserOperationsDaoTest
{

    @BeforeEach
    void initiate()
    {
        PasswordOperations operate = new PasswordOperationsImpl();
        GroupOperationsDao groupOperate = new GroupOperationsDao();
//        MasterUsersOperationsDao.add("Manash", operate.encryptPassword("qwerty"));
//        MasterUsersOperationsDao.add("Suresh", operate.encryptPassword("bfb"));
//        MasterUsersOperationsDao.add("Roshan", operate.encryptPassword("dewdw"));
//        MasterUsersOperationsDao.add("Lokesh", operate.encryptPassword("odhc"));
    }

    @Test
    @DisplayName(value = "Adding a proper group name in Master User")
    void addGroupNameTest1()
    {
        User user = MasterUsersOperationsDao.getUser("Manash").get();
        Assertions.assertTrue(MasterUserOperationsDao.addGroup(user,"Group 1"));
    }

    @Test
    @DisplayName(value = "Adding a group name in null Master User")
    void addGroupNameTest2()
    {
        Assertions.assertFalse(MasterUserOperationsDao.addGroup(null,"Group 1"));
    }

    @Test
    @DisplayName(value = "Adding a group name in wrong Master User")
    void addGroupNameTest3()
    {
        User user = MasterUsersOperationsDao.getUser("Manash").get();
        Assertions.assertFalse(MasterUserOperationsDao.addGroup(null,"Group 1"));
    }

    @Test
    @DisplayName(value = "Adding a wrong group name in Master User")
    void getMasterUsersTest2()
    {
        User user = MasterUsersOperationsDao.getUser("Manash").get();
        Assertions.assertFalse(MasterUserOperationsDao.addGroup(user,""));
    }
}
