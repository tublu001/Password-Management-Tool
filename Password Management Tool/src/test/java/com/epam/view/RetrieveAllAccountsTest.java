package com.epam.view;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.view.RetrieveAllAccounts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

public class RetrieveAllAccountsTest
{

    @BeforeEach
    void initiate()
    {

    }

    @InjectMocks
    RetrieveAllAccounts retrieveAllAccounts;

    @Test
    @DisplayName(value = "retriving all Master User Accounts of correct user")
    void retrieveAllAccountsTest1()
    {
        String receivedException = null;
        try
        {
            retrieveAllAccounts.execute(null);
        }
        catch(Exception e)
        {
            receivedException = e.getClass().getSimpleName();
        }
        Assertions.assertTrue(receivedException.equals("NullPointerException"));

    }

    @Test
    @DisplayName(value = "retriving all Master User Accounts of wrong user")
    void retrieveAllAccountsTest2()
    {
        String receivedException = null;
        try
        {
            retrieveAllAccounts.execute(null);
        }
        catch(Exception e)
        {
            receivedException = e.getClass().getSimpleName();
        }
        Assertions.assertFalse(receivedException.equals("NoSuchElementException"));

    }
}
