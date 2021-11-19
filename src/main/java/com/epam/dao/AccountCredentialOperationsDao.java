package com.epam.dao;

import java.util.ArrayList;

import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PwdOperate;

public class AccountCredentialOperationsDao implements AccountsControllerDao
{

	public AccountCredentialOperationsDao() 
	{}
	
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
		
		System.out.println("\nAccount Added...\n\n");
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
			System.out.println("Account Removed...");
			return true;
		}
		else
			System.out.println("Some Error Occured");
		
		return false;
	}

	@Override
	public boolean isAppName(UserAccount account, String appName)
	{
			if(account.getAppName().equals(appName))
				return true;
			else
				return false;
	}
	
	

	@Override
	public void showAccount(UserAccount account) 
	{
		System.out.println(account);
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
