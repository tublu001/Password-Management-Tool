package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.UI.HomeMenu;
import com.epam.dao.GroupOperationsDao;
import com.epam.model.MasterUsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RenameGroupName implements UserAccountCrudOperation 
{
	private static final Logger LOGGER = LogManager.getLogger(RenameGroupName.class);
	Scanner input = new Scanner(System.in);
	GroupOperationsDao goperate = new GroupOperationsDao();
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		LOGGER.info("\n\n|------------Rename Group--------------|\n");
		goperate.showGroups(user);
		LOGGER.info("\nChoose any group you want to rename: ");
		int groupNum = input.nextInt();
		input.nextLine();	//consume new line character
		if(goperate.isGroupIndex(user, groupNum-1))
		{
			String oldGroupName = goperate.getGroup(user, groupNum-1);
			LOGGER.info("Give a new Group name: ");
			String newGroupName = input.nextLine();
			goperate.updateGroupName(user, groupNum-1, newGroupName);
			goperate.updateAccountGroupName(user, oldGroupName, newGroupName);
			LOGGER.info("Group Name Updated Successfully.. (" + oldGroupName + " ----> " + newGroupName +")");
//			return true;
		}
		else
			LOGGER.info("Invalid Selection..\n\n");
//		return false;
		return Optional.ofNullable(user);
	}

}
