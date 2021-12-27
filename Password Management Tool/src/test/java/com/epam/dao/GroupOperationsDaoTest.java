package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GroupOperationsDaoTest
{

    @InjectMocks
    private GroupOperationsDao underTest;

    @Nested
    class WhenCheckingIfIsGroupAvailable
    {
        private final String GROUP_NAME = "GROUP_NAME";
        @Mock
        private User user;
        @Mock
        private UserAccount account1;
        @Mock
        private UserAccount account2;

        @Test
        void isGroupPresent() throws UserException
        {
            user = new User();
            account1 = new UserAccount("a", "vsgvsgvs", "qwerty", "grp1", user);
            account2 = new UserAccount("b", "vsgvsgvs", "qwerty", "grp1", user);
            user.getAccounts().add(account1);
            user.getAccounts().add(account2);
            user.getGroups().add(account1.getAccountGroup());
            user.getGroups().add(account2.getAccountGroup());
            Assertions.assertTrue(underTest.isGroupAvailable(user, "grp1"));
            Assertions.assertFalse(underTest.isGroupAvailable(user, "none"));
        }

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenAddingGroupName
    {
        private final String GROUP_NAME = "GROUP_NAME";
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenShowingGroups
    {
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenGettingGroup
    {
        private final int INDEX = 95;
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenUpdatingGroupName
    {
        private final int INDEX = 0;
        private final String NEW_GROUP_NAME = "NEW_GROUP_NAME";
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenGettingGroupWiseAccounts
    {
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenGettingGroupAccounts
    {
        private final String GROUP_NAME = "GROUP_NAME";
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenCheckingIfIsGroupIndex
    {
        private final int INDEX = 65;
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenUpdatingAccountGroupName
    {
        private final String OLD_GROUP_NAME = "OLD_GROUP_NAME";
        private final String NEW_GROUP_NAME = "NEW_GROUP_NAME";
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }
}