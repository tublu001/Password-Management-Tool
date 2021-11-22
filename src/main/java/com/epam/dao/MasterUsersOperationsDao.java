package com.epam.dao;

import java.util.ArrayList;

import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.MasterUsersDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterUsersOperationsDao
{
	private static final Logger LOGGER = LogManager.getLogger(MasterUserOperationsDao.class);
	static ArrayList<MasterUsers> masterUsers = MasterUsersDB.getMasterUsers(); 
	
	public static MasterUsers add(String userName, String password) 
	{
//		DB = MasterUsersDB.getMasterUsers();
		MasterUsers user = new MasterUsers();
		user.setUserName(userName);
		user.setPassword(password);
		user.setAccounts(new ArrayList<>());
		user.setGroups(new ArrayList<>());
		user.getGroups().add("Undefined");
		user.setPrefPass(new PreferredPassword());
		masterUsers.add(user);
		return user;
	}

	public static void showUsers()
	{
		for(MasterUsers users : masterUsers)
			LOGGER.info(users.toString());
	}
	


	public static MasterUsers getUser(String userName) 
	{
		for(MasterUsers user : masterUsers)
		{
			if(userName.equals(user.getUserName()))
				return user;
		}
		return null;
	}
	
	public static boolean isMasterPresent(String userNm)
	{
		for(MasterUsers user : masterUsers)
			if(userNm.equals(user.getUserName()))
				return true;
		return false;
	}
}
