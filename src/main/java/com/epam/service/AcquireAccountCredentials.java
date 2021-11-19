package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.UI.GroupMenu;
import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PwdOperate;

public class AcquireAccountCredentials implements UserAccountCrudOperation 
{
	Scanner input = new Scanner(System.in);
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		System.out.print("\n\nStore Account credentials\n\nEnter App Name: ");
		String appName = input.nextLine();
		if(!new AccountCredentialOperationsDao().isAppPresent(user, appName))
		{
			System.out.print("Enter URL: ");
			String url = input.nextLine();
			System.out.print("Press enter to generate a new password for ("+appName+")..  ");
			input.nextLine();
			
			//Password generation and encryption
			PasswordOperations operate = new PwdOperate();
	        String pwd = operate.generatePassword(user);
	        String encPwd = operate.encryptPassword(pwd);
	        
	        System.out.println("\n\nPassword generated as per your preference. Copy this password and use it in your application:\n" + pwd);
	        System.out.print("\nPress enter for setting up Group\n");
	        input.nextLine();
	        String groupName = GroupMenu.showGroupUI(user);
			user = new AccountCredentialOperationsDao().store(user, appName, url, encPwd, groupName);
			return Optional.ofNullable(user);
		}
		System.out.println("App already present in Database... Try again..\n");
		return Optional.ofNullable(user);
	}

}
