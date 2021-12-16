package com.epam.controller;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.GroupOperationsDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.dto.UserAccountDTO;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.RepositoryDB;
import com.epam.service.UserLoginValidation;
import com.epam.utility.Utility;
import com.epam.view.GroupMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest(classes = WebAccountCrudRequestHandler.class)
//@ContextConfiguration(classes = WebAccountCrudRequestHandler.class)
//@WebMvcTest(value = WebAccountCrudRequestHandler.class)
//@WithMockUser
class WebAccountCrudRequestHandlerTest
{
    @MockBean
    private AccountsControllerDao accountsControllerDao;
    @MockBean
    private GroupOperationsDao groupOperations;
    @MockBean
    private MasterUserOperationsDao masterUserOperationsDao;
    @MockBean
    private PasswordOperations passwordOperations;
    @MockBean
    private MasterUsersOperationsDao masterUsersOperationsDao;
    @Mock
    private GroupMenu groupMenu;
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

    @InjectMocks
    WebAccountCrudRequestHandler underTest;

    @BeforeEach
    void initiate()
    {
        user = new User("Manash", "qwerty", new ArrayList<>(), new ArrayList<>(), preferredPassword);
        account1 = new UserAccount("a", "vsgvsgvs", "qwerty", "grp1", user);
        account2 = new UserAccount("b", "vsgvsgvs", "hello", "grp2", user);

        userDetail = new UserAccountDTO(user, "c", "url", "sccavv", "G1");
        userDetail1 = new UserAccountDTO(user, "d", "url", "sccavv", "G1");

        user.getAccounts().add(account1);
        user.getAccounts().add(account2);

        user.getGroups().add("grp1");
        user.getGroups().add("grp2");

    }

    @Test
    void storeAccountDetails()
    {
    }

    @Test
    void retrievePassword()
    {
    }

    @Test
    void renameGroupName()
    {
    }

    @Test
    void deleteAccountCredential()
    {
    }

    @Test
    void setPreferredPassword()
    {
    }

    @Test
    void accountCrudMenu()
    {
//        when(masterUsersOperationsDao.getUser(userId).get()).thenReturn(Optional.ofNullable(user));
//        assertEquals("crudMenu", underTest.accountCrudMenu().getViewName());
    }
}