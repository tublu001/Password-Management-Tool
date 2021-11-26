package com.epam.dao;

import java.util.ArrayList;

import com.epam.model.MasterUser;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.MasterUsersDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterUsersOperationsDao
{
	private static final Logger LOGGER = LogManager.getLogger(MasterUserOperationsDao.class);
	static ArrayList<MasterUser> masterUsers = MasterUsersDB.getMasterUsers();
	
	public static boolean add(String userName, String password)
	{
		MasterUser user = null;
		boolean status = false;
		if(userName != null && password != null && userName != "" && password != "") {
			user = new MasterUser();
			user.setUserName(userName);
			user.setPassword(password);
			user.setAccounts(new ArrayList<>());
			user.setGroups(new ArrayList<>());
			user.getGroups().add("Undefined");
			user.setPrefPass(new PreferredPassword());
			status = masterUsers.add(user);
		}
		return status;
	}

	public static void showUsers()
	{
		for(MasterUser users : masterUsers)
			LOGGER.info(users.toString());
	}
	


	public static MasterUser getUser(String userName)
	{
		MasterUser master = null;
		for(MasterUser user : masterUsers)
			if(userName.equals(user.getUserName()))
				master = user;
		return master;
	}
	
	public static boolean isMasterPresent(String userNm)
	{
		for(MasterUser user : masterUsers)
			if(userNm.equals(user.getUserName()))
				return true;
		return false;
	}
}
