package com.epam.passwordOperations;

import java.util.Scanner;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidationImpl implements UserValidation
{
	private static final Logger LOGGER = LogManager.getLogger(UserValidationImpl.class);
	Scanner input = new Scanner(System.in);
	PasswordOperations operate = new PasswordOperationsImpl();
	
	@Override
	public User validateMaster()
	{
		User user = null;
		LOGGER.info("\nEnter Your MASTER Account credentials - \n\nUser Name: ");
		String userName = input.nextLine();

		if(validateUserName(userName))
			user = MasterUsersOperationsDao.getUser(userName).get();
		if(user != null)
		{
			LOGGER.info("\n\nEnter your (Master) password: ");
			String password = input.nextLine();
			if(validatePassword(user, password))
			{
				LOGGER.info("\nLogging you in");
			}
			else
				user = null;
		}
		return user;
	}

	@Override
	public boolean validateUserName(String userName)
	{
		boolean isUserName = false;
		if(userName != null && userName != "")
			if(MasterUsersOperationsDao.isMasterPresent(userName))
			{
				isUserName = true;
			}
			else
			{
				LOGGER.info("User Not Found...");
			}
		else
		{
			LOGGER.info("Invalid User Name..");
		}
		return isUserName;
	}

	@Override
	public boolean validatePassword(User user, String password)
	{
		boolean isPassword = false;
		if(password != null && password != "")
		{
			isPassword = operate.encryptPassword(password).equals(user.getPassword());
		}
		if(isPassword)
		{
			LOGGER.info("Verified...");
		}
		else
		{
			LOGGER.info("Incorrect Password");
		}

		return isPassword;
	}

	

}
