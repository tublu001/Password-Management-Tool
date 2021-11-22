package com.epam.dao;

import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterUserOperationsDao 
{
	private static final Logger LOGGER = LogManager.getLogger(MasterUserOperationsDao.class);
	public static void addGroup(MasterUsers user, String groupName) {
		user.getGroups().add(groupName);
	}
	
	public static void showAccounts(MasterUsers user)
	{
		for(UserAccount accs : user.getAccounts())
			LOGGER.info(accs);
	}
	
	public boolean remove(String user) 
	{
		//Implement it
		return false;
	}
}
