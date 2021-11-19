package com.epam.UI;

import java.util.Scanner;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordValidate;
import com.epam.passwordOperations.PwdOperate;
import com.epam.passwordOperations.UserValidate;

public class MasterOperations implements MasterCrudMenu
{

	Scanner input = new Scanner(System.in);
	
	public MasterOperations() {}
	
	static
	{
		PasswordOperations operate = new PwdOperate();
		MasterUsersOperationsDao.add("Manash", operate.encryptPassword("qwerty"));
		MasterUsersOperationsDao.add("Suresh", operate.encryptPassword("bfb"));
		MasterUsersOperationsDao.add("Roshan", operate.encryptPassword("dewdw"));
		MasterUsersOperationsDao.add("Lokesh", operate.encryptPassword("odhc"));
	}

	@Override
	public void createMaster() 
	{
		System.out.print("\n\nCreate a new Master Account\n\nUser Name: ");
		String userName = input.nextLine();
		if(!MasterUsersOperationsDao.isMasterPresent(userName))
		{
			System.out.print("Enter a New Password: ");
			String password = input.nextLine();
			
			PasswordOperations operate = new PwdOperate();
			
			MasterUsersOperationsDao.add(userName, operate.encryptPassword(password));
			System.out.print("\nMaster User created");
		}
		else
			System.out.print("\nFailed.. Master already present in database..\n\n");
	}

	@Override
	public void showAllMasters() 
	{
		MasterUsersOperationsDao.showUsers();
	}

	@Override
	public MasterUsers loginMaster() 
	{
		PasswordValidate pv = new UserValidate();
        MasterUsers user = pv.validateUser();
		return user;
	}

}

