package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.dao.GroupOperationsDao;
import com.epam.model.MasterUsers;

public class RenameGroupName implements UserAccountCrudOperation 
{
	Scanner input = new Scanner(System.in);
	GroupOperationsDao goperate = new GroupOperationsDao();
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		System.out.println("\n\n|------------Rename Group--------------|\n");
		goperate.showGroups(user);
		System.out.print("\nChoose any group you want to rename: ");
		int groupNum = input.nextInt();
		input.nextLine();	//consume new line character
		if(goperate.isGroupIndex(user, groupNum-1))
		{
			String oldGroupName = goperate.getGroup(user, groupNum-1);
			System.out.println("Give a new Group name: ");
			String newGroupName = input.nextLine();
			goperate.updateGroupName(user, groupNum-1, newGroupName);
			goperate.updateAccountGroupName(user, oldGroupName, newGroupName);
			System.out.println("Group Name Updated Successfully.. (" + oldGroupName + " ----> " + newGroupName +")");
//			return true;
		}
		else
			System.out.println("Invalid Selection..\n\n");
//		return false;
		return Optional.ofNullable(user);
	}

}
