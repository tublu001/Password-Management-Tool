package com.epam.AccountGroups;

import java.util.Scanner;

import com.epam.MasterGroups.MasterUsers;

public class GroupCrudOperations implements AccountCrudGroup {

	public GroupCrudOperations() 
	{}
	
	Scanner sc = new Scanner(System.in);
	GroupOperations goperate = new GroupOperations();
	
	@Override
	public boolean createGroup(MasterUsers user) 
	{
		System.out.println("Enter a new Group Name: ");
		String groupName = sc.nextLine();
		if(!goperate.isGroupAvailable(user, groupName))
		{
			goperate.addGroupName(user, groupName);
			return true;
		}
		System.out.println("Group already present in the Database\n");
		return false;
	}

	@Override
	public String storeInExistingGroup(MasterUsers user) 
	{
		System.out.println("\n\nAll the Existing Groups Available: ");
		goperate.showGroups(user);
		System.out.println("\nSelect any one: ");
		int groupNum = sc.nextInt();
		if(goperate.isGroupIndex(user, groupNum-1))
		{
			String groupName = goperate.getGroup(user, groupNum-1);
			if(goperate.isGroupAvailable(user, groupName))
				return groupName;
			else
				System.out.println("Group not available...");
		}
		return "Undefined";
	}	
}
