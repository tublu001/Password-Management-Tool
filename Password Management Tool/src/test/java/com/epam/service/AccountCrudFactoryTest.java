package com.epam.service;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class AccountCrudFactoryTest
{

    @Mock
    MasterUsersOperationsDao masterUsersOperationsDao;

    @BeforeEach
    void initiate() throws UserException
    {
    }

    @Test
    @DisplayName(value = "Capturing Objects Test")
    void retrieveAllAccountsTest1() throws UserException
    {
        String exception = null;
        try
        {
            new AccountCrudFactory().getOperation(String.valueOf(4));
        } catch (Exception e)
        {
            exception = e.getClass().getName();

        }
        Assertions.assertTrue(exception.contains("NullPointerException"));
    }

    @Test
    @DisplayName(value = "Capturing Objects Test 2")
    void getObjectTest() throws UserException
    {
        String exception = null;
        try
        {
            new AccountCrudFactory().getObject(String.valueOf(9));
        } catch (Exception e)
        {
            exception = e.getClass().getName();

        }
        Assertions.assertTrue(exception.contains("NullPointerException"));
    }

}