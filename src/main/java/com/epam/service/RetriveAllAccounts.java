package com.epam.service;

import java.util.Optional;

import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.model.MasterUser;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetriveAllAccounts implements UserAccountCrudOperation {
	private static final Logger LOGGER = LogManager.getLogger(RetriveAllAccounts.class);
	@Override
	public Optional<MasterUser> execute(MasterUser user)
	{
		if(user != null)
			return MasterUserOperationsDao.showAccounts(user);
		else
			return Optional.empty();
	}

}
