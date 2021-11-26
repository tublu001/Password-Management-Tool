package com.epam.dao;

import com.epam.model.MasterUser;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class MasterUserOperationsDao 
{
	private static final Logger LOGGER = LogManager.getLogger(MasterUserOperationsDao.class);
	public static boolean addGroup(MasterUser user, String groupName)
	{
		boolean isAdded = false;
		if(user != null && groupName != null && groupName != "")
			isAdded = user.getGroups().add(groupName);
		return isAdded;
	}
	
	public static Optional<MasterUser> showAccounts(MasterUser user)
	{
		LOGGER.info("\n\nAll Account Details\n\n");
		int sl = 1;
		for(UserAccount account : user.getAccounts())
		{
			LOGGER.info(sl++ + ". ");
			new AccountCredentialOperationsDao().showAccount(account);
		}
		return Optional.ofNullable(user);
	}
}
