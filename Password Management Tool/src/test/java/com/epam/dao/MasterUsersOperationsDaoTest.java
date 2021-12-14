package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.repository.MySQL_DB;
import com.epam.repository.RepositoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MasterUsersOperationsDaoTest
{

    @InjectMocks
    private MasterUsersOperationsDao underTest;

    @Mock
    private RepositoryDB database = new MySQL_DB();

    @Nested
    class WhenAdding
    {
        private final String USER_NAME = "USER_NAME";
        private final String PASSWORD = "PASSWORD";

        @Test
        void addMasterUserTest() throws UserException
        {
            when(database.setMasterUser(new User())).thenReturn(Optional.empty());
//            assertFalse(MasterUsersOperationsDao.add(USER_NAME, PASSWORD).isPresent());
        }

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenShowingUsers
    {
        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenGettingUser
    {
        private final String USER_NAME = "USER_NAME";

        @BeforeEach
        void setup()
        {
        }
    }

    @Nested
    class WhenCheckingIfIsMasterPresent
    {
        private final String USER_NAME = "USER_NAME";

        @BeforeEach
        void setup()
        {
        }
    }
}