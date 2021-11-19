package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;

public class RetriveAccountPassword implements UserAccountCrudOperation 
{
	Scanner input = new Scanner(System.in);
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		System.out.print("\n\nRetrive Account password\n\nEnter App Name: ");
		String appName = input.nextLine();
		AccountCredentialOperationsDao op = new AccountCredentialOperationsDao();
		boolean isApp = false;
		
		for(UserAccount account : user.getAccounts())
		{
			if(op.isAppName(account, appName))
			{
				isApp = true;
				System.out.println("Account password is: " + op.retrivePassword(account));
				break;
			}
		}
		if(!isApp)
			System.out.println("App not found...\n");
		
		return Optional.ofNullable(user);
	}

}
