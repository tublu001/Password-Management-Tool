package com.epam.userAccounts;

import com.epam.MasterGroups.MasterUsers;

public interface AccountsController 
{
	public MasterUsers store(MasterUsers user, String appName, String url, String password, String groupName);
	public String retrivePassword(UserAccount account);
	public void showAccount(UserAccount account);
	public boolean remove(MasterUsers user, UserAccount account);
	public boolean isAppName(UserAccount account, String appName);
	public boolean isAppPresent(MasterUsers user, String appName);
}
