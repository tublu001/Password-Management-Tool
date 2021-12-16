package com.epam.service;

import com.epam.application.Application;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import com.epam.application.Application;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.service.UserLoginValidationImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserLoginValidationImplTest
{

    @Mock
    MasterUsersOperationsDao masterUsersOperationsDao;
    @InjectMocks
    UserLoginValidationImpl uV;

    @Test
    @DisplayName(value = "Validating correct Master")
    void validateMasterUserTest1() throws UserException
    {
        when(masterUsersOperationsDao.isMasterPresent(anyString())).thenReturn(true);
        Assertions.assertTrue(uV.validateUserName("Manash"));
    }
    //
    @Test
    @DisplayName(value = "Validating wrong Master User Name")
    void validateMasterUserTest2() throws UserException
    {
        String receivedException = null;
        try
        {
            Assertions.assertFalse(uV.validateUserName(null));
        }
        catch(Exception e)
        {
            receivedException = e.getClass().getSimpleName();
        }
        Assertions.assertTrue(receivedException.equals("UserException"));
    }

    @Test
    @DisplayName(value = "Validating wrong Master Password")
    void validateMasterUserTest3() throws UserException
    {
        String receivedException = null;
        try
        {
            Assertions.assertFalse(uV.validateUserName(""));
        }
        catch(Exception e)
        {
            receivedException = e.getClass().getSimpleName();
        }
        Assertions.assertTrue(receivedException.equals("UserException"));
    }

    @Test
    @DisplayName(value = "Validating null Master User Name")
    void validateMasterUserTest4() throws UserException
    {
        String receivedException = null;
        try
        {
            Assertions.assertFalse(uV.validateUserName("None"));
        }
        catch(Exception e)
        {
            receivedException = e.getClass().getSimpleName();
        }
        Assertions.assertTrue(receivedException.equals("UserException"));
    }

    @Test
    @DisplayName(value = "Validating null Master Password")
    void validateMasterUserTest5() throws UserException
    {
        when(masterUsersOperationsDao.isMasterPresent(anyString())).thenReturn(false);
        String receivedException = null;
        try
        {
            Assertions.assertFalse(uV.validateUserName("None"));
        }
        catch(Exception e)
        {
            receivedException = e.getClass().getSimpleName();
        }
        Assertions.assertTrue(receivedException.equals("UserException"));

    }


}
