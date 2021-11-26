package com.epam;

import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PwdOperate;
import com.epam.passwordOperations.UserValidate;
import com.epam.passwordOperations.UserValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserValidateTest
{
    UserValidation uV;

    @BeforeEach
    void initiate()
    {
        PasswordOperations operate = new PwdOperate();
        uV = new UserValidate();
        MasterUsersOperationsDao.add("Manash", operate.encryptPassword("qwerty"));
        MasterUsersOperationsDao.add("Suresh", operate.encryptPassword("bfb"));
        MasterUsersOperationsDao.add("Roshan", operate.encryptPassword("dewdw"));
        MasterUsersOperationsDao.add("Lokesh", operate.encryptPassword("odhc"));
    }

    @Test
    @DisplayName(value = "Validating correct Master")
    void validateMasterUserTest1()
    {
        Assertions.assertTrue(uV.validateUserName("Manash") && uV.validatePassword(uV.getMasterUser("Manash"), "qwerty"));
    }

    @Test
    @DisplayName(value = "Validating wrong Master User Name")
    void validateMasterUserTest2()
    {
        Assertions.assertFalse(uV.validateUserName("None") && uV.validatePassword(uV.getMasterUser("Manash"), "qwerty"));
    }

    @Test
    @DisplayName(value = "Validating wrong Master Password")
    void validateMasterUserTest3()
    {
        Assertions.assertFalse(uV.validateUserName("Manash") && uV.validatePassword(uV.getMasterUser("Manash"), "None"));
    }

    @Test
    @DisplayName(value = "Validating null Master User Name")
    void validateMasterUserTest4()
    {
        Assertions.assertFalse(uV.validateUserName(null) && uV.validatePassword(uV.getMasterUser("Manash"), "qwerty"));
    }

    @Test
    @DisplayName(value = "Validating null Master Password")
    void validateMasterUserTest5()
    {
        Assertions.assertFalse(uV.validateUserName("Manash") && uV.validatePassword(uV.getMasterUser("Manash"), null));
    }


}
