package com.epam.dao;

import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;

public class MasterUserOperationsDao 
{
	public static void addGroup(MasterUsers user, String groupName) {
		user.getGroups().add(groupName);
	}
	
	public static void showAccounts(MasterUsers user)
	{
		for(UserAccount accs : user.getAccounts())
			System.out.println(accs);
	}
	
	public boolean remove(String user) 
	{
		//Implement it
		return false;
	}
}
