package com.epam.passwordOperations;

import java.util.Scanner;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.model.MasterUsers;

public class UserValidate implements PasswordValidate
{

	public UserValidate() {}
	Scanner input = new Scanner(System.in);
	PasswordOperations operate = new PwdOperate();
	
	@Override
	public MasterUsers validateUser() 
	{
		System.out.print("\nEnter Your MASTER Account credentials - \n\nUser Name: ");
		String userName = input.nextLine();
		MasterUsers user = MasterUsersOperationsDao.getUser(userName);
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
		System.out.println("\n\nEnter your (Master) password: ");
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
