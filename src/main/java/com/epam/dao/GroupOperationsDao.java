package com.epam.dao;

import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class GroupOperationsDao
{
	private static final Logger LOGGER = LogManager.getLogger(GroupOperationsDao.class);
	
	public boolean isGroupAvailable(User user, String groupName)
	{
		for(String dBGroupName : user.getGroups())
			if(dBGroupName.equals(groupName))
				return true;
		return false;
	}
	
	public String addGroupName(User user, String groupName)
	{
		String groupAdded = null;
		if(MasterUserOperationsDao.addGroup(user, groupName))
			groupAdded = groupName;

		return groupAdded;
	}
	
	public void showGroups(User user)
	{
		AtomicInteger count = new AtomicInteger();
		if(user != null)
			user.getGroups().forEach(groupName->LOGGER.info(count.incrementAndGet() + ". " +groupName));
	}
	
	public String getGroup(User user, int index)
	{
		if(user != null && (index < user.getGroups().size() && index > 0))
			return user.getGroups().get(index);
		else
			LOGGER.info("Invalid Input");
		return "";
	}
	
	public boolean updateGroupName(User user, int index, String newGroupName)
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
	
	
	public void getGroupWiseAccounts(User user)
	{
		LOGGER.info("\n\n|--------------Group Wise All Available Accounts--------------|\n");
		if(user != null)
			user.getGroups().forEach(groupName ->
					{
				LOGGER.info("\n              "+groupName+"");
				LOGGER.info("--------------------------------");
				getGroupAccounts(user, groupName);
			});
		LOGGER.info("\n");
	}
	
	
	void getGroupAccounts(User user, String groupName)
	{
		AtomicInteger count = new AtomicInteger();
		if(user != null)
			user.getAccounts().forEach(account ->
					{
						if (groupName.equals(account.getAccountGroup()))
							LOGGER.info(count.incrementAndGet() + ". " + account);
					});
	}

	public boolean isGroupIndex(User user, int index)
	{
		return index < user.getGroups().size() && index > 0;
	}

	public void updateAccountGroupName(User user, String oldGroupName, String newGroupName)
	{
		if(user != null)
			user.getAccounts().forEach(account->
			{
				if(oldGroupName.equals(account.getAccountGroup()))
					account.setAccountGroup(newGroupName);
			});
	}
	
	
	
	

}
