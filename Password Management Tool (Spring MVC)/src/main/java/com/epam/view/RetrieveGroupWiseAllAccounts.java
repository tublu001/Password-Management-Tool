package com.epam.view;

import com.epam.dao.GroupOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetrieveGroupWiseAllAccounts implements UserAccountCrudOperation
{
    @Autowired
    private GroupOperationsDao groupOperations;

    @Override
    public Optional<User> execute(User user) throws UserException
    {
        if (user != null)
        {
            groupOperations.getGroupWiseAccounts(user);
            return Optional.ofNullable(user);
        } else
            return Optional.empty();
    }

}
