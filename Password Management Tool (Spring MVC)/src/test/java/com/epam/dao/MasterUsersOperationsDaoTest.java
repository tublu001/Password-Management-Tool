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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MasterUsersOperationsDaoTest
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
    private MasterUsersOperationsDao underTest;

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

    private final String USER_NAME = "USER_NAME";
    private final String PASSWORD = "PASSWORD";

    @Test
    void addMasterUserTest() throws UserException
    {
        when(utility.isValidString(anyString())).thenReturn(true);
        when(database.setMasterUser(any(User.class))).thenReturn(Optional.ofNullable(user));
//        when()
//        Assertions.assertEquals(1, database.getGroups().size());
        assertTrue(underTest.addMasterUser(USER_NAME, PASSWORD).isPresent());
//        Assertions.assertEquals(2, user.getGroups().size());
    }

    @Test
    void WhenShowingUsers()
    {

    }

    @Test
    void WhenGettingUser() throws UserException
    {
        User user1 = new User("suresh", "qwerty", new ArrayList<>(), new ArrayList<>(), preferredPassword);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        when(database.findAll()).thenReturn(users);
        try
        {
            assertTrue(underTest.getUser("suresh").isPresent());
        } catch (Exception e)
        {
            assertTrue(e.getClass().getSimpleName().equals("UserException"));
        }
    }

    @Test
    void WhenGettingUserNotExists()
    {
        User user1 = new User("suresh", "qwerty", new ArrayList<>(), new ArrayList<>(), preferredPassword);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        when(database.findAll()).thenReturn(users);
        try
        {
            assertFalse(underTest.getUser("asascx").isPresent());
        } catch (Exception e)
        {
            assertTrue(e.getClass().getSimpleName().equals("IndexOutOfBoundsException"));
        }
    }

    @Test
    void WhenCheckingIfIsMasterPresent() throws UserException
    {
        User user1 = new User("suresh", "qwerty", new ArrayList<>(), new ArrayList<>(), preferredPassword);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        when(database.findAll()).thenReturn(users);
        when(utility.isValidString(anyString())).thenReturn(true);
        assertTrue(underTest.isMasterPresent("suresh"));
        assertFalse(underTest.isMasterPresent("asascx"));
    }
}