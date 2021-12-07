package com.epam.user_interface;

import java.util.Optional;
import java.util.Scanner;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.model.User;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PasswordOperationsImpl;

import com.epam.repository.MySQL_DB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeMenu 
{
	private static final Logger LOGGER = LogManager.getLogger(HomeMenu.class);
	public HomeMenu() 
	{}
	
	@SuppressWarnings(value = {"all"})
	public static void showHomeUI()
	{

		int flag = 0;
		final Scanner input = new Scanner(System.in);
		
		while(flag==0)
		{
			MySQL_DB.initialize();
			LOGGER.info("\n\n*************** WELCOME TO PASSWORD MANAGEMENT TOOL ***************\n\n");
			LOGGER.info("1. Log In to your Master Account");
			LOGGER.info("2. Sign Up for a Master Account");
			LOGGER.info("3. Retrive all Master user names");
			LOGGER.info("0. Exit..\n\n\nChoose Any: ");
			String selection = "invalid";
			try
			{
				selection = input.next();
			}
			catch (Exception e)
			{
				LOGGER.info(e.getMessage());
			}

			
			MasterCrudMenu op = new MasterOperations();
			switch(selection)
			{
				case "1":
					Optional<User> user = op.loginMaster();
					if(user.isPresent())
						AccountMenuCRUD.showCrudMenu(user.get());
					break;
				case "2":
					op.createMaster();
					break;
				case "3":
					op.showAllMasters();
					break;
				case "0":
					flag = 1;
					LOGGER.info("Thank you... Exiting...");
					break;
				default:
					LOGGER.warn("Invalid Input! Try again...");
					break;
			}
		}
	}

}
