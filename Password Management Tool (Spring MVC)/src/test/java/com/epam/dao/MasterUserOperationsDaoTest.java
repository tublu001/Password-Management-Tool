package com.epam.dao;

import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.repository.RepositoryDB;
import com.epam.service.UserLoginValidation;
import com.epam.service.password_operations.PasswordOperations;
import com.epam.service.password_operations.PreferredPassword;
import com.epam.utility.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MasterUserOperationsDaoTest
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
    private MasterUserOperationsDao underTest;

    @BeforeEach
    void setup()
    {
        user = new User("Manash", "qwerty", new ArrayList<>(), new ArrayList<>(), preferredPassword);
        account1 = new UserAccount("a", "vsgvsgvs", "qwerty", "grp1", user);
        account2 = new UserAccount("b", "vsgvsgvs", "hello", "grp1", user);

        userDetail = new UserAccountDTO(user, "c", "url", "sccavv", "G1");
        userDetail1 = new UserAccountDTO(user, "d", "url", "sccavv", "G1");

        user.getAccounts().add(account1);
        user.getAccounts().add(account2);

        user.getGroups().add("grp1");
        user.getGroups().add("grp2");
    }


    @Test
    void WhenAddingGroup() throws UserException
    {
        when(utility.isValidString(anyString())).thenReturn(true);
        Assertions.assertEquals(2, user.getGroups().size());
//        Assertions.assertTrue(underTest.addGroup(Optional.ofNullable(user), "newlyAdded"));
        Assertions.assertEquals(2, user.getGroups().size());
    }

    @Test
    void WhenShowingAccounts()
    {

    }
}