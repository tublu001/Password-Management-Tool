package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.PasswordValidate;
import com.epam.passwordOperations.UserValidate;

public class DeleteAccountCredential implements UserAccountCrudOperation 
{
	Scanner input = new Scanner(System.in);
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		PasswordValidate pv = new UserValidate();
		AccountCredentialOperationsDao op = new AccountCredentialOperationsDao();
		System.out.print("\n\nDelete Account credential\n\nEnter App Name: ");
		String appName = input.nextLine();
		boolean isApp = false;
		
		for(UserAccount account : user.getAccounts())
		{
			if(op.isAppName(account, appName))
			{
				isApp = true;
				System.out.println("Application Found : "+ appName);
				if(pv.validatePassword(user))
					op.remove(user, account);
				else
					System.out.println("Incorrect Password...");
				break;
			}
		}
		if(!isApp)
			System.out.println("App not found...\n");
		
		return null;
	}

}
