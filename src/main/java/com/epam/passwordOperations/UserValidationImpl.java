package com.epam.passwordOperations;

import java.util.Optional;
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
	public Optional<User> validateMaster()
	{
		Optional<User> user = Optional.empty();
		LOGGER.info("\nEnter Your MASTER Account credentials - \n\nUser Name: ");
		String userName = input.nextLine();

		if(validateUserName(userName))
		{
			user = MasterUsersOperationsDao.getUser(userName);
		}
		if(user.isPresent())
		{
			LOGGER.info("\n\nEnter your (Master) password: ");
			String password = input.nextLine();
			if(validatePassword(user.get(), password))
			{
				LOGGER.info("\nLogging you in");
			}
			else
			{
				user = Optional.empty();
			}
		}
		return user;
	}

	@Override
	public boolean validateUserName(String userName)
	{
		boolean isUserName = false;
		if(!userName.equals(null) && !userName.equals(""))
		{
			if (MasterUsersOperationsDao.isMasterPresent(userName))
			{
				isUserName = true;
			}
			else
			{
				LOGGER.info("User Not Found...");
			}
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
		if(!password.equals(null) && !password.equals(""))
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
