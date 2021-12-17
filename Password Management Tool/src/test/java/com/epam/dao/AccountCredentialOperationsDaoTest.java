package com.epam.dao;

import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.service.passwordOperations.PasswordOperations;
import com.epam.service.passwordOperations.PreferredPassword;
import com.epam.repository.RepositoryDB;
import com.epam.service.UserLoginValidation;
import com.epam.utility.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountCredentialOperationsDaoTest
{

    @Mock
    private PasswordOperations passwordOperations;
    @Mock
    private RepositoryDB database;
    @Mock
    private UserAccountDTO userDetail, userDetail1;
    @Mock
    private UserAccount account;
    @Mock
    private User user;
    @Mock
    private UserAccount account1, account2;
    @Mock
    private PreferredPassword preferredPassword;
    @Mock
    private Utility utility;
    @Mock
    private GroupOperationsDao groupOperationsDao;
    @Mock
    private UserLoginValidation userLoginValidation;

    @InjectMocks
    private AccountCredentialOperationsDao underTest;


    @BeforeEach
    void setUp()
    {
        user = new User("Manash", "qwerty", new ArrayList<>(), new ArrayList<>(), preferredPassword);
        account1 = new UserAccount("a", "vsgvsgvs", "qwerty", "grp1", user);
        account2 = new UserAccount("b", "vsgvsgvs", "hello", "grp1", user);

        userDetail = new UserAccountDTO(user, "c", "url", "sccavv", "G1");
        userDetail1 = new UserAccountDTO(user, "d", "url", "sccavv", "G1");

        user.getAccounts().add(account1);
        user.getAccounts().add(account2);

    }


    @Test
    void storeTest() throws UserException
    {
        when(utility.isValidString(anyString())).thenReturn(true);
        when(utility.isValidString(anyString())).thenReturn(true);
        when(utility.isValidString(anyString())).thenReturn(true);
        when(utility.isValidString(anyString())).thenReturn(true);
        when(groupOperationsDao.isGroupAvailable(user, userDetail.getGroupName())).thenReturn(true);
        when(passwordOperations.encryptPassword(userDetail.getPassword())).thenReturn("qwerty");

        when(database.merge(user)).thenReturn(Optional.ofNullable(user));
        Assertions.assertTrue(underTest.storeAccount(userDetail));
        Assertions.assertEquals(3, user.getAccounts().size());
    }


    @Test
    void retrievePasswordTest() throws UserException
    {
        when(passwordOperations.decryptPassword(anyString())).thenReturn("qwerty");
        when(utility.isValidString(anyString())).thenReturn(true);
        Assertions.assertEquals("qwerty", underTest.retrievePassword(user, "a"));
    }


    @Test
    void removeObject() throws UserException
    {
//        when(database.merge(user)).thenReturn(Optional.ofNullable(user));
//        when(userLoginValidation.validatePassword(user, user.getPassword())).thenReturn(true);
//        underTest.remove(user, "a", "qwerty");
//        Assertions.assertEquals(1, user.getAccounts().size());
//        user.getAccounts().forEach(System.out::println);
    }

    @Test
    void checkAppName()
    {
        User user = new User();
        account1 = new UserAccount("a", "vsgvsgvs", "qwerty", "grp1", user);
        account2 = new UserAccount("b", "vsgvsgvs", "qwerty", "grp1", user);
        user.getAccounts().add(account1);
        user.getAccounts().add(account2);
        assertTrue(underTest.isAppName(user, "a"));
        assertFalse(underTest.isAppName(user, "c"));
    }


    @Test
    void isAppPresentTest() throws UserException
    {
        assertTrue(underTest.isAppPresent(user, "a"));
        assertFalse(underTest.isAppPresent(user, "notPresent"));
    }

    @Test
    void getAccountByAppNameTest() throws UserException
    {
        assertTrue(underTest.getAccountByAppName(user, "a").isPresent());
    }

    @Test
    void isExecuting()
    {
//        underTest.showAccount(account1);
//        verify(underTest, atLeastOnce()).showAccount(account);
    }

}