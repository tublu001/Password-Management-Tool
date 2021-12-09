package com.epam.dao;

import com.epam.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MasterUserOperationsDaoTest
{

    @InjectMocks
    private MasterUserOperationsDao underTest;

    @Nested
    class WhenAddingGroup
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
    class WhenShowingAccounts
    {
        @Mock
        private User user;

        @BeforeEach
        void setup()
        {
        }
    }
}