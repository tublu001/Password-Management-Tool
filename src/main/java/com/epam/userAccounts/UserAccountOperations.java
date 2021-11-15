package com.epam.userAccounts;

import java.util.Scanner;

import com.epam.MasterGroups.MasterUsers;
import com.epam.MasterGroups.UserVerify;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordValidate;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.passwordOperations.PwdOperate;

public class UserAccountOperations implements CrudMenuOperations
{

	public UserAccountOperations() 
	{}
	
	Scanner input = new Scanner(System.in);
	
	@Override
	public MasterUsers acquireAccountCredentials(MasterUsers user) 
	{
		System.out.print("\n\nStore Account credentials\n\nEnter App Name: ");
		String appName = input.nextLine();
		System.out.print("Enter URL: ");
		String url = input.nextLine();
		System.out.print("Press enter to generate a new password for ("+appName+")..  ");
		input.nextLine();
		
		//Password generation and encryption
		PasswordOperations operate = new PwdOperate();
        String pwd = operate.generatePassword(user);
        String encPwd = operate.encryptPassword(pwd);
        
        System.out.println("\n\nPassword generated as per your preference. Use it in your application:\n" + pwd);
        System.out.print("\nPress enter to save");
        input.nextLine();
        
		user = new AccountCredentialOperations().store(user, appName, url, encPwd);
		return user;
	}

	@Override
	public void retriveAccountCredential(MasterUsers user) 
	{
		System.out.print("\n\nRetrive Account password\n\nEnter App Name: ");
		String appName = input.nextLine();
		AccountCredentialOperations op = new AccountCredentialOperations();
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
	}

	@Override
	public MasterUsers deleteAccountCredential(MasterUsers user) 
	{
		PasswordValidate pv = new UserVerify();
		AccountCredentialOperations op = new AccountCredentialOperations();
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

	@Override
	public void retriveAllAccounts(MasterUsers user) 
	{
		System.out.print("\n\nRetrive All Account Credentials\n\n");
		int sl = 1;
		for(UserAccount account : user.getAccounts())
		{
			System.out.print(sl++ + ". ");
			new AccountCredentialOperations().showAccount(account);
		}
	}

	@Override
	public void setPasswordPreference(MasterUsers user) 
	{
		PreferredPassword pp = user.getPrefPass();
		pp.setPrefferdPassword();
	}

}
