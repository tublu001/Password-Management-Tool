package com.epam.view;

import com.epam.dao.MasterUserOperationsDao;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetrieveAllAccounts implements UserAccountCrudOperation
{
    @Autowired
    private MasterUserOperationsDao masterUserOperationsDao;

    @Override
    public Optional<User> execute(User user)
    {
        if (user != null)
        {
            return masterUserOperationsDao.showAccounts(user);
        } else
        {
            return Optional.empty();
        }
    }

}
