package com.epam.UI;

import java.util.Scanner;

import com.epam.AccountGroups.AccountCrudGroup;
import com.epam.AccountGroups.GroupCrudOperations;
import com.epam.AccountGroups.GroupOperations;
import com.epam.MasterGroups.MasterUsers;

public class GroupMenu 
{

	public GroupMenu() 
	{}
	
	
	@SuppressWarnings(value = {"all"})
	public static String showGroupUI(MasterUsers user)
	{
		int flag = 0;
		String groupName = "Undefined";
		final Scanner input = new Scanner(System.in);
		GroupOperations goperate = new GroupOperations();
		
		while(flag==0)
		{
			System.out.println("1. Create a new group");
			System.out.println("2. Store in a existing group");
			System.out.print("0. Skip..(Ungrouped)\n\n\nChoose Any: ");
			
			char ch = input.next().charAt(0);
			
			AccountCrudGroup op = new GroupCrudOperations();
			switch(ch)
			{
				case '1':
					if(op.createGroup(user))
					{
						int lastItem = user.getGroups().size();
						groupName = user.getGroups().get(lastItem-1);
						flag = 1;
					}
					break;
				case '2':
					groupName = op.storeInExistingGroup(user);
					flag = 1;
					break;
//				case '3':
//					op.showAllMasters();
//					break;
				case '0':
					flag = 1;
					break;
				default:
					System.out.println("Invalid Input! Try again...");
					break;
			}
		}
		return groupName;
	}

}
