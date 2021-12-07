package com.epam.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.model.User;
import com.epam.repository.MySQL_DB;
import com.epam.repository.RepositoryDB;

public class MasterUsersOperationsDao
{
	private static final Logger LOGGER = LogManager.getLogger(MasterUsersOperationsDao.class);
	static RepositoryDB database = new MySQL_DB();
	static List<User> users;

	
	public static boolean add(String userName, String password)
	{
		User user;
		boolean status = false;
		if(!userName.equals(null) && !password.equals(null) && !userName.equals("") && !password.equals("")) {
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
		users.forEach(LOGGER::info);
	}
	


	public static Optional<User> getUser(String userName)
	{
		users = database.getMasterUsers();
		User master = null;
//		users.forEach(user ->
//		{
//			if(userName.equals(user.getUserName()))
//			{master = user;}
//		});
		for(User user : users)
			if(userName.equals(user.getUserName()))
				master = user;
		return  Optional.ofNullable(master);
	}

	public static boolean isMasterPresent(String userName)
	{
		users = database.getMasterUsers();
//		return users.forEach(user -> {if(userName.equals(user.getUserName()))
//			return user;
//		});
		boolean isMasterPresent = false;
		for(User user : users)
		{
			if(userName.equals(user.getUserName()))
			{
				isMasterPresent = true;
			}
		}
		return isMasterPresent;
	}
}
