package com.epam.user_interface;

import java.util.Optional;
import java.util.Scanner;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.passwordOperations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterOperations implements MasterCrudMenu
{
	private static final Logger LOGGER = LogManager.getLogger(MasterOperations.class);
	Scanner input = new Scanner(System.in);
	
	public MasterOperations() {}
	

	@Override
	public Optional<User> createMaster() throws UserException
	{
		Optional<User> user;
		LOGGER.info("\n\nCreate a new Master Account\n\nUser Name: ");
		String userName = input.nextLine();
		if(!MasterUsersOperationsDao.isMasterPresent(userName))
		{
			LOGGER.info("Enter a New Password: ");
			String password = input.nextLine();
			
			PasswordOperations operate = new PasswordOperationsImpl();
			
			user = MasterUsersOperationsDao.add(userName, operate.encryptPassword(password));
			LOGGER.info("\nMaster User created");
		}
		else
		{
			throw new UserException("Failed.. Master already present in database..");
		}
		return user;
	}

	@Override
	public void showAllMasters() 
	{
		MasterUsersOperationsDao.showUsers();
	}

	@Override
	public Optional<User> loginMaster() throws UserException
	{
		UserLoginValidation uv = new UserLoginValidationImpl();
		Optional<User> user = uv.validateMaster();

		if (user.isEmpty())
			throw new UserException("User not Found!!!");

		AccountMenuCRUD.showCrudMenu(user.get());
		return user;
	}

}

