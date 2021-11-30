package com.epam.dao;

import com.epam.model.User;
import com.epam.model.UserAccount;

public interface AccountsControllerDao 
{
	boolean store(UserData userDetail);
	String retrivePassword(UserAccount account);
	void showAccount(UserAccount account);
	boolean remove(User user, UserAccount account);
	boolean isAppName(UserAccount account, String appName);
	boolean isAppPresent(User user, String appName);
}
