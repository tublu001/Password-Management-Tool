package com.epam.dao;

import java.util.ArrayList;
import java.util.Optional;

import com.epam.model.User;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.MasterUsersDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterUsersOperationsDao
{
	private static final Logger LOGGER = LogManager.getLogger(MasterUserOperationsDao.class);
	static ArrayList<User> users = MasterUsersDB.getMasterUsers();
	
	public static boolean add(String userName, String password)
	{
		User user = null;
		boolean status = false;
		if(userName != null && password != null && userName != "" && password != "") {
			user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			user.setAccounts(new ArrayList<>());
			user.setGroups(new ArrayList<>());
			user.getGroups().add("Undefined");
			user.setPrefPass(new PreferredPassword());
			status = users.add(user);
		}
		return status;
	}

	public static void showUsers()
	{
		for(User users : users)
			LOGGER.info(users.toString());
	}
	


	public static Optional<User> getUser(String userName)
	{
		User master = null;
		for(User user : users)
			if(userName.equals(user.getUserName()))
				master = user;
		return Optional.ofNullable(master);
	}
	
	public static boolean isMasterPresent(String userNm)
	{
		for(User user : users)
			if(userNm.equals(user.getUserName()))
				return true;
		return false;
	}
}
