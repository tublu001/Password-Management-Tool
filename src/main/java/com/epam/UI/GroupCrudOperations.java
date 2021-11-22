package com.epam.UI;

import java.util.Scanner;

import com.epam.dao.GroupOperationsDao;
import com.epam.model.MasterUsers;
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
	public boolean createGroup(MasterUsers user) 
	{
		LOGGER.info("Enter a new Group Name: ");
		String groupName = sc.nextLine();
		if(!goperate.isGroupAvailable(user, groupName))
		{
			goperate.addGroupName(user, groupName);
			return true;
		}
		LOGGER.info("Group already present in the Database\n");
		return false;
	}

	@Override
	public String storeInExistingGroup(MasterUsers user) 
	{
		LOGGER.info("\n\nAll the Existing Groups Available: ");
		goperate.showGroups(user);
		LOGGER.info("\nSelect any one: ");
		int groupNum = sc.nextInt();
		if(goperate.isGroupIndex(user, groupNum-1))
		{
			String groupName = goperate.getGroup(user, groupNum-1);
			if(goperate.isGroupAvailable(user, groupName))
				return groupName;
			else
				LOGGER.info("Group not available...");
		}
		return "Undefined";
	}	
}
