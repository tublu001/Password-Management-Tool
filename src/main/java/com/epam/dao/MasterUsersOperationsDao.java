package com.epam.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.model.User;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.repository.MySQL_DB;
import com.epam.repository.RepositoryDB;

public class MasterUsersOperationsDao
{
	private static final Logger LOGGER = LogManager.getLogger(MasterUserOperationsDao.class);
	static RepositoryDB database = new MySQL_DB();
	static List<User> users;

	
	public static boolean add(String userName, String password)
	{
		User user = null;
		boolean status = false;
		if(userName != null && password != null && userName != "" && password != "") {
			user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			user.getGroups().add("Undefined");
			status = database.setMasterUser(user);
		}
		return status;
	}

	public static void showUsers()
	{
		users = database.getMasterUsers();
		for(Object users : users)
			LOGGER.info(users.toString());
	}
	


	public static Optional<User> getUser(String userName)
	{
		users = database.getMasterUsers();
		User master = null;
		for(User user : users)
			if(userName.equals(user.getUserName()))
				master =  user;
		return Optional.ofNullable(master);
	}
	
	public static boolean isMasterPresent(String userName)
	{
		users = database.getMasterUsers();
		for(User user : users)
			if(userName.equals(user.getUserName()))
				return true;
		return false;
	}
}
