package com.epam.AccountGroups;

import com.epam.MasterGroups.MasterUsers;
import com.epam.userAccounts.UserAccount;

public class GroupOperations 
{

	public GroupOperations() 
	{}
	
	boolean isGroupAvailable(MasterUsers user, String groupName)
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
		user.addGroup(groupName);
		return groupName;
	}
	
	public void showGroups(MasterUsers user)
	{
		int count = 0;
		if(user != null)
			for(String groupName : user.getGroups())
				System.out.println(++count + ". " +groupName);
	}
	
	public String getGroup(MasterUsers user, int index)
	{
		if(user != null && (index < user.getGroups().size() && index > 0))
			return user.getGroups().get(index);
		else
			System.out.println("Invalid Input");
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
			System.out.println("Invalid Input");
		return false;
	}
	
	
	public void getGroupWiseAccounts(MasterUsers user)
	{
		System.out.println("\n\n|--------------Group Wise All Available Accounts--------------|\n");
		if(user != null)
			for(String groupName : user.getGroups())
			{
				System.out.println("\n              "+groupName+"");
				System.out.println("--------------------------------");
				getGroupAccounts(user, groupName);
			}
		System.out.println("\n");
	}
	
	
	void getGroupAccounts(MasterUsers user, String groupName)
	{
		int count = 0;
		if(user != null)
			for(UserAccount account : user.getAccounts())
			{
				if(groupName.equals(account.getGroup()))
					System.out.println(++count + ". " + account);
			}
	}

	public boolean isGroupIndex(MasterUsers user, int index) 
	{
		if(index < user.getGroups().size() && index > 0)
			return true;
		return false;
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
