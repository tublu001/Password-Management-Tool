package com.epam.dao;

import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.service.UserLoginValidation;
import com.epam.repository.RepositoryDB;
import com.epam.utility.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class GroupOperationsDaoTest
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
    private UserLoginValidation userLoginValidation;

    @InjectMocks
    private GroupOperationsDao underTest;

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
    @DisplayName(value = "Getting proper group name from User")
    void getGroupNameTest1() throws UserException
    {
        System.out.println(user.getGroups().size());
        System.out.println(underTest.getGroup(user, 1));
        Assertions.assertTrue("grp2".equals(underTest.getGroup(user, 1)));
    }

    @Test
    @DisplayName(value = "Getting group name from null User")
    void getGroupNameTest2() throws UserException
    {
        GroupOperationsDao underTest = new GroupOperationsDao();
        Assertions.assertFalse("grp2".equals(underTest.getGroup(user, 0)));
    }

    @Test
    @DisplayName(value = "update group name of User")
    void updateGroupNameTest1() throws UserException
    {
        Assertions.assertTrue("grp2".equals(underTest.getGroup(user, 1)));
        Assertions.assertTrue(underTest.updateGroupName(user, 1, "group2"));
        Assertions.assertTrue("group2".equals(underTest.getGroup(user, 1)));
    }

    @Test
    @DisplayName(value = "update group name of null User")
    void updateGroupNameTest2() throws UserException
    {
        Assertions.assertTrue("grp2".equals(underTest.getGroup(user, 1)));
        Assertions.assertTrue(underTest.updateGroupName(user, 1, "new group"));
        Assertions.assertFalse("new group".equals(underTest.getGroup(user, 0)));
    }
}
