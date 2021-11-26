package com.epam.dao;

import com.epam.model.MasterUser;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GroupOperationsDao
{
	private static final Logger LOGGER = LogManager.getLogger(GroupOperationsDao.class);
	
	public boolean isGroupAvailable(MasterUser user, String groupName)
	{
		for(String dbGroupName : user.getGroups())
		{
			if(groupName.equals(dbGroupName))
				return true;
		}
		return false;
	}
	
	public String addGroupName(MasterUser user, String groupName)
	{
		String groupAdded = null;
		if(MasterUserOperationsDao.addGroup(user, groupName))
			groupAdded = groupName;

		return groupAdded;
	}
	
	public void showGroups(MasterUser user)
	{
		int count = 0;
		if(user != null)
			for(String groupName : user.getGroups())
				LOGGER.info(++count + ". " +groupName);
	}
	
	public String getGroup(MasterUser user, int index)
	{
		if(user != null && (index < user.getGroups().size() && index > 0))
			return user.getGroups().get(index);
		else
			LOGGER.info("Invalid Input");
		return "";
	}
	
	public boolean updateGroupName(MasterUser user, int index, String newGroupName)
	{
		boolean groupUpdated = false;
		if(index < user.getGroups().size() && index > 0)
		{
			user.getGroups().set(index, newGroupName);
			groupUpdated = true;
		}
		else
			LOGGER.info("Invalid Input");
		return groupUpdated;
	}
	
	
	public void getGroupWiseAccounts(MasterUser user)
	{
		LOGGER.info("\n\n|--------------Group Wise All Available Accounts--------------|\n");
		if(user != null)
			for(String groupName : user.getGroups())
			{
				LOGGER.info("\n              "+groupName+"");
				LOGGER.info("--------------------------------");
				getGroupAccounts(user, groupName);
			}
		LOGGER.info("\n");
	}
	
	
	void getGroupAccounts(MasterUser user, String groupName)
	{
		int count = 0;
		if(user != null)
			for(UserAccount account : user.getAccounts())
			{
				if(groupName.equals(account.getGroup()))
					LOGGER.info(++count + ". " + account);
			}
	}

	public boolean isGroupIndex(MasterUser user, int index)
	{
		return index < user.getGroups().size() && index > 0;
	}

	public void updateAccountGroupName(MasterUser user, String oldGroupName, String newGroupName)
	{
		if(user != null)
			for(UserAccount account : user.getAccounts())
			{
				if(oldGroupName.equals(account.getGroup()))
					account.setGroup(newGroupName);
			}
	}
	
	
	
	

}
