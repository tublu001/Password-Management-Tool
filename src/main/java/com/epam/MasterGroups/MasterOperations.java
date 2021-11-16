package com.epam.MasterGroups;

import java.util.Scanner;

import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordValidate;
import com.epam.passwordOperations.PwdOperate;

import java.util.ArrayList;

public class MasterOperations implements MasterCrudMenu
{

	Scanner input = new Scanner(System.in);
	
	public MasterOperations() {}

	@Override
	public void createMaster() 
	{
		System.out.print("\n\nCreate a new Master Account\n\nUser Name: ");
		String userName = input.nextLine();
		if(!MasterUsers.isMasterPresent(userName))
		{
			System.out.print("Enter a New Password: ");
			String password = input.nextLine();
			
			PasswordOperations operate = new PwdOperate();
			ArrayList<MasterUsers> masterusers = MasterUsers.getMasterUsers();
			
			masterusers.add(new MasterUsers().add(userName, operate.encryptPassword(password)));
			System.out.print("\nMaster User created");
		}
		else
			System.out.print("\nFailed.. Master already present in database..\n\n");
	}

	@Override
	public void showAllMasters() 
	{
		MasterUsers.showUsers();
	}

	@Override
	public MasterUsers loginMaster() 
	{
		PasswordValidate pv = new UserVerify();
        MasterUsers user = pv.validateUser();
		return user;
	}

}
