package com.epam.view;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.GroupOperationsDao;
import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.service.UserLoginValidation;
import com.epam.repository.RepositoryDB;
import com.epam.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Scanner;

class AcquireAccountCredentialsTest
{
    @Mock
    private AccountsControllerDao accountsControllerDao;
    @Mock
    private GroupMenu groupMenu;
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
    @MockBean
    private Utility utility;
    @Mock
    private GroupOperationsDao groupOperationsDao;
    @Mock
    private UserLoginValidation userLoginValidation;

    Scanner input = new Scanner(System.in);

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
    void execute() throws UserException
    {
//        when(utility.isValidString(anyString())).thenReturn(true);
//        when(accountsControllerDao.isAppPresent(user, anyString())).thenReturn(false);
//        when(input.nextLine()).thenReturn("qwerty");
//        when(passwordOperations.generatePassword(user)).thenReturn("1gey7fg378yb");
//        when(groupMenu.showGroupUI(user)).thenReturn("G11");
//        when(accountsControllerDao.store(userDetail)).thenReturn(true);
//        assertTrue(new AcquireAccountCredentials().execute(user).isPresent());
    }
}