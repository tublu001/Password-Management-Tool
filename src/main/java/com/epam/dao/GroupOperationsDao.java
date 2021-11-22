package com.epam.dao;

import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GroupOperationsDao
{
	private static final Logger LOGGER = LogManager.getLogger(GroupOperationsDao.class);
	public GroupOperationsDao() 
	{}
	
	public boolean isGroupAvailable(MasterUsers user, String groupName)
	{
		for(String dbGroupName : user.getGroups())
		{
			if(groupName.equals(dbGroupName))
				return true;
		}
		return false;
	}
	
	public String addGroupName(MasterUsers user, String groupName)
	{
		MasterUserOperationsDao.addGroup(user, groupName);
		return groupName;
	}
	
	public void showGroups(MasterUsers user)
	{
		int count = 0;
		if(user != null)
			for(String groupName : user.getGroups())
				LOGGER.info(++count + ". " +groupName);
	}
	
	public String getGroup(MasterUsers user, int index)
	{
		if(user != null && (index < user.getGroups().size() && index > 0))
			return user.getGroups().get(index);
		else
			LOGGER.info("Invalid Input");
		return "";
	}
	
	public boolean updateGroupName(MasterUsers user, int index, String newGroupName)
	{
		if(index < user.getGroups().size() && index > 0)
		{
			user.getGroups().set(index, newGroupName);
			return true;
		}
		else
			LOGGER.info("Invalid Input");
		return false;
	}
	
	
	public void getGroupWiseAccounts(MasterUsers user)
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
	
	
	void getGroupAccounts(MasterUsers user, String groupName)
	{
		int count = 0;
		if(user != null)
			for(UserAccount account : user.getAccounts())
			{
				if(groupName.equals(account.getGroup()))
					LOGGER.info(++count + ". " + account);
			}
	}

	public boolean isGroupIndex(MasterUsers user, int index) 
	{
		return index < user.getGroups().size() && index > 0;
	}

	public void updateAccountGroupName(MasterUsers user, String oldGroupName, String newGroupName) 
	{
		if(user != null)
			for(UserAccount account : user.getAccounts())
			{
				if(oldGroupName.equals(account.getGroup()))
					account.setGroup(newGroupName);
			}
	}
	
	
	
	

}
