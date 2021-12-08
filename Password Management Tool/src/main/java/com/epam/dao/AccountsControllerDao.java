package com.epam.dao;

import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.model.UserData;

public interface AccountsControllerDao 
{
	boolean store(UserData userDetail) throws UserException;
	String retrievePassword(UserAccount account);
	void showAccount(UserAccount account);
	boolean remove(User user, UserAccount account) throws UserException;
	boolean isAppName(UserAccount account, String appName);
	boolean isAppPresent(User user, String appName) throws UserException;
}
