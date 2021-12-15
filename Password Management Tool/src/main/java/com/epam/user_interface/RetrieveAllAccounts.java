package com.epam.user_interface;

import com.epam.dao.MasterUserOperationsDao;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetrieveAllAccounts implements UserAccountCrudOperation
{
    private static final Logger LOGGER = LogManager.getLogger(RetrieveAllAccounts.class);

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
