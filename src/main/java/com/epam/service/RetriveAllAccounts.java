package com.epam.service;

import java.util.Optional;

import com.epam.UI.HomeMenu;
import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetriveAllAccounts implements UserAccountCrudOperation {
	private static final Logger LOGGER = LogManager.getLogger(RetriveAllAccounts.class);
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		LOGGER.info("\n\nAll Account Details\n\n");
		int sl = 1;
		for(UserAccount account : user.getAccounts())
		{
			LOGGER.info(sl++ + ". ");
			new AccountCredentialOperationsDao().showAccount(account);
		}
		return Optional.of(user);
	}

}
