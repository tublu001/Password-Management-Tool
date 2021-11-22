package com.epam.dao;

import java.util.ArrayList;

import com.epam.UI.HomeMenu;
import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PwdOperate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountCredentialOperationsDao implements AccountsControllerDao
{

	public AccountCredentialOperationsDao() 
	{}
	private static final Logger LOGGER = LogManager.getLogger(AccountCredentialOperationsDao.class);
	PasswordOperations operate = new PwdOperate();
	
	@Override
	public MasterUsers store(MasterUsers user, String appName, String url, String password, String groupName) 
	{
		ArrayList<UserAccount> allAccounts = user.getAccounts();
		UserAccount newAccount = new UserAccount();
		
		newAccount.setAppName(appName);
		newAccount.setUrl(url);
		newAccount.setPassword(password);
		newAccount.setGroup(groupName);
		allAccounts.add(newAccount);
		
		LOGGER.info("\nAccount Added...\n\n");
		return user;
	}	
	
	@Override
	public String retrivePassword(UserAccount account) 
	{
		return operate.decryptPassword(account.getPassword());
	}
	
	@Override
	public boolean remove(MasterUsers user, UserAccount account) 
	{
		if(user.getAccounts().remove(account))
		{
			LOGGER.info("Account Removed...");
			return true;
		}
		else
			LOGGER.info("Some Error Occured");
		
		return false;
	}

	@Override
	public boolean isAppName(UserAccount account, String appName)
	{
			return account.getAppName().equals(appName);
	}
	
	

	@Override
	public void showAccount(UserAccount account) 
	{
		LOGGER.info(account);
	}

	@Override
	public boolean isAppPresent(MasterUsers user, String appName) 
	{
		for(UserAccount account : user.getAccounts())
			if(isAppName(account, appName))
				return true;
		
		return false;
	}
}
