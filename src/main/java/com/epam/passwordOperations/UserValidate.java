package com.epam.passwordOperations;

import java.util.Scanner;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.model.MasterUsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidate implements UserValidation
{
	private static final Logger LOGGER = LogManager.getLogger(UserValidate.class);
	public UserValidate() {}
	Scanner input = new Scanner(System.in);
	PasswordOperations operate = new PwdOperate();
	
	@Override
	public MasterUsers validateUser() 
	{
		LOGGER.info("\nEnter Your MASTER Account credentials - \n\nUser Name: ");
		String userName = input.nextLine();
		MasterUsers user = MasterUsersOperationsDao.getUser(userName);
		if( user != null)
		{
			LOGGER.info("User Found...");
			if(validatePassword(user))
			{
				LOGGER.info("\nLogging you in");
				return user;
			}
			else
				return null;
		}
		LOGGER.info("User not found..");
		return null;
	}

	@Override
	public boolean validatePassword(MasterUsers user) 
	{
		LOGGER.info("\n\nEnter your (Master) password: ");
		String password = input.nextLine();
		if(operate.encryptPassword(password).equals(user.getPassword()))
		{
			LOGGER.info("Verified...");
			return true;
		}
		else
			LOGGER.info("Incorrect Password");
		return false;
	}

	

}
