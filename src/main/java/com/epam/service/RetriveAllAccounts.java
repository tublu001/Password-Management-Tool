package com.epam.service;

import java.util.Optional;

import com.epam.dao.MasterUserOperationsDao;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetriveAllAccounts implements UserAccountCrudOperation {
	private static final Logger LOGGER = LogManager.getLogger(RetriveAllAccounts.class);
	@Override
	public Optional<User> execute(User user)
	{
		if(user != null)
			return MasterUserOperationsDao.showAccounts(user);
		else
			return Optional.empty();
	}

}
