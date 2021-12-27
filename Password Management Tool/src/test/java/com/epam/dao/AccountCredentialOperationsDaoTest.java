package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.model.UserData;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.repository.RepositoryDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountCredentialOperationsDaoTest
{

    @Mock
    private PasswordOperations operate;
    @Mock
    private RepositoryDB database;
    @InjectMocks
    private AccountCredentialOperationsDao underTest;

    @Nested
    class WhenStoring
    {
        @Mock
        private UserData userDetail, userDetail1;

        @Test
        void storeTest() throws UserException
        {
            User user = new User();
            userDetail = new UserData(user, "a", "vsgvsgvs", "qwerty", "grp1");
            userDetail1 = new UserData(user, "b", "vsgvsgvs", "qwerty", "grp1");
            when(database.merge(user)).thenReturn(Optional.empty());
            Assertions.assertTrue(underTest.store(userDetail));
            Assertions.assertTrue(underTest.store(userDetail1));
            Assertions.assertEquals(2, user.getAccounts().size());

        }

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenRetrivingPassword
    {
        @Mock
        private UserAccount account;

        @Test
        void retrivePassword()
        {
            User user = new User();
            account = new UserAccount("a", "vsgvsgvs", "IECQGzreFylRp0dpPcAHXA==", "grp1", user);
            when(operate.decryptPassword(account.getPassword())).thenReturn("qwerty");
            Assertions.assertTrue(underTest.retrievePassword(account).equals("qwerty"));
        }

        @BeforeEach
        void setup()
        {

//            Assertions.assertTrue(underTest.store(userDetail));
        }
    }

    @Nested
    class WhenRemoving
    {
        @Mock
        private User user;
        @Mock
        private UserAccount account1, account2;

        @Test
        void removeObject() throws UserException
        {
            user = new User();
            account1 = new UserAccount("a", "vsgvsgvs", "qwerty", "grp1", user);
            account2 = new UserAccount("b", "vsgvsgvs", "qwerty", "grp1", user);
            user.getAccounts().add(account1);
            user.getAccounts().add(account2);
            when(database.merge(user)).thenReturn(Optional.empty());
            underTest.remove(user, account1);
            Assertions.assertEquals(1, user.getAccounts().size());
            user.getAccounts().forEach(System.out::println);
////
        }

        @BeforeEach
        void setup()
        {

        }
    }

    @Nested
    class WhenCheckingIfIsAppName
    {
        private final String APP_NAME = "APP_NAME";
        @Mock
        private UserAccount account1;
        @Mock
        private UserAccount account2;

        @Test
        void checkAppName()
        {
            User user = new User();
            account1 = new UserAccount("a", "vsgvsgvs", "qwerty", "grp1", user);
            account2 = new UserAccount("b", "vsgvsgvs", "qwerty", "grp1", user);
            user.getAccounts().add(account1);
            user.getAccounts().add(account2);
            assertTrue(underTest.isAppName(account1, "a"));
            assertFalse(underTest.isAppName(account2, "a"));
        }

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenShowingAccount
    {
        @Mock
        private UserAccount account;

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenCheckingIfIsAppPresent
    {
        private final String APP_NAME = "APP_NAME";
        @Mock
        private User user;
        @Mock
        private UserAccount account1;
        @Mock
        private UserAccount account2;

        @Test
        void isAppPresentTest() throws UserException
        {
            user = new User();
            account1 = new UserAccount("a", "vsgvsgvs", "qwerty", "grp1", user);
            account2 = new UserAccount("b", "vsgvsgvs", "qwerty", "grp1", user);
            user.getAccounts().add(account1);
            user.getAccounts().add(account2);

            assertTrue(underTest.isAppPresent(user, "a"));
            assertFalse(underTest.isAppPresent(user, "c"));
        }

        @BeforeEach
        void setup()
        {
        }
    }
}