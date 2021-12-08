package com.epam.user_interface;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.epam.dao.GroupOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GroupCrudOperations implements AccountCrudGroup
{
	private static final Logger LOGGER = LogManager.getLogger(GroupCrudOperations.class);
	public GroupCrudOperations() 
	{}
	
	Scanner sc = new Scanner(System.in);
	GroupOperationsDao goperate = new GroupOperationsDao();
	
	@Override
	public boolean createGroup(User user) throws UserException
	{
		LOGGER.info("Enter a new Group Name: ");
		String groupName = sc.nextLine();
		boolean groupCreated = false;
		if(!goperate.isGroupAvailable(user, groupName))
		{
			goperate.addGroupName(user, groupName);
			groupCreated = true;
		}
		else
		{
			throw new UserException("Group already exists in Database!!!");
		}

		return groupCreated;
	}

	@Override
	public String storeInExistingGroup(User user) throws UserException, InputMismatchException
	{
		LOGGER.info("\n\nAll the Existing Groups Available: ");
		goperate.showGroups(user);
		LOGGER.info("\nSelect any one: ");
		int groupNum = sc.nextInt();
		String grpName = "Undefined";
		if(goperate.isGroupIndex(user, groupNum-1))
		{
			String groupName = goperate.getGroup(user, groupNum-1);
			if(goperate.isGroupAvailable(user, groupName))
			{
				grpName = groupName;
			}
		}
		return grpName;
	}	
}
