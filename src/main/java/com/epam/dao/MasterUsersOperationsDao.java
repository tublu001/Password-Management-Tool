package com.epam.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.epam.exceptions.UserException;
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

	
	public static boolean add(String userName, String password) throws UserException
	{
		User user;
		boolean status = false;
		if(userName.equals(null) || password.equals(null) || userName.equals("") || password.equals(""))
		{
			throw new UserException("Invalid User Name provided!!!");
		}
		user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.getGroups().add("Undefined");
		status = database.setMasterUser(user);
		if(!status)
			throw new UserException("Some Errors occured... Cannot add User to the Database!!!");
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
		for(User user : users)
			if(userName.equals(user.getUserName()))
				master = user;
		return  Optional.ofNullable(master);
	}

	public static boolean isMasterPresent(String userName) throws UserException
	{
		if(userName.equals(null) || userName.equals(""))
		{
			throw new UserException("Invalid User Name provided!!!");
		}
		users = database.getMasterUsers();
		boolean isMasterPresent = !(users.stream()
				.filter(user -> userName.equals(user.getUserName()))
				.collect(Collectors.toList())
				.isEmpty());
		return isMasterPresent;
	}
}
