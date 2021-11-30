package com.epam;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordOperationsImpl;
import com.epam.service.AccountCrudFactory;
import com.epam.service.UserAccountCrudOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountCrudFactoryTest {

    @BeforeEach
    void initiate() {
        PasswordOperations operate = new PasswordOperationsImpl();
        MasterUsersOperationsDao.add("Manash", operate.encryptPassword("qwerty"));
        MasterUsersOperationsDao.add("Suresh", operate.encryptPassword("bfb"));
        MasterUsersOperationsDao.add("Roshan", operate.encryptPassword("dewdw"));
        MasterUsersOperationsDao.add("Lokesh", operate.encryptPassword("odhc"));
    }

    @Test
    @DisplayName(value = "Capturing Objects Test")
    void retriveAllAccountsTest1()
    {
        UserAccountCrudOperation operations;
        for(int x=1; x < 11; x++) {
            operations = new AccountCrudFactory().getOperation(String.valueOf(x));
            if(x<8) {
                System.out.println(x);
                Assertions.assertNotNull(operations);
            }
            else
            {
                System.out.println(x);
                Assertions.assertNull(operations);
            }
            operations = new AccountCrudFactory().getOperation("0");
            Assertions.assertNull(operations);
        }
    }
}