package com.epam.dao;

import com.epam.model.MasterUser;
import com.epam.model.UserAccount;

public interface AccountsControllerDao 
{
	boolean store(MasterUser user, String appName, String url, String password, String groupName);
	String retrivePassword(UserAccount account);
	void showAccount(UserAccount account);
	boolean remove(MasterUser user, UserAccount account);
	boolean isAppName(UserAccount account, String appName);
	boolean isAppPresent(MasterUser user, String appName);
}
