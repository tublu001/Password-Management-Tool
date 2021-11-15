package com.epam.MasterGroups;

import java.util.Scanner;

import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordValidate;
import com.epam.passwordOperations.PwdOperate;

public class UserVerify implements PasswordValidate
{

	public UserVerify() 
	{}
	Scanner input = new Scanner(System.in);
	PasswordOperations operate = new PwdOperate();
	
	@Override
	public MasterUsers validateUser() 
	{
		System.out.print("\nEnter Your MASTER Account credentials - \n\nUser Name: ");
		String userName = input.nextLine();
		MasterUsers user = MasterUsers.getUser(userName);
		if( user != null)
		{
			System.out.print("User Found...");
			if(validatePassword(user))
			{
				System.out.println("\nLogging you in");
				return user;
			}
			else
				return null;
		}
		System.out.println("User not found..");
		return null;
	}

	@Override
	public boolean validatePassword(MasterUsers user) 
	{
		System.out.println("\n\nEnter your password: ");
		String password = input.nextLine();
		if(operate.encryptPassword(password).equals(user.getPassword()))
		{
			System.out.println("Verified...");
			return true;
		}
		else
			System.out.println("Incorrect Password");
		return false;
	}

	

}
