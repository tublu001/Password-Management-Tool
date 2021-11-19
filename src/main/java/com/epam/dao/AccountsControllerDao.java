package com.epam.dao;

import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;

public interface AccountsControllerDao 
{
	public MasterUsers store(MasterUsers user, String appName, String url, String password, String groupName);
	public String retrivePassword(UserAccount account);
	public void showAccount(UserAccount account);
	public boolean remove(MasterUsers user, UserAccount account);
	public boolean isAppName(UserAccount account, String appName);
	public boolean isAppPresent(MasterUsers user, String appName);
}
