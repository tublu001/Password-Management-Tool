package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.UserValidationImpl;
import com.epam.passwordOperations.UserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAccountCredential implements UserAccountCrudOperation 
{
	private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCredential.class);
	Scanner input = new Scanner(System.in);
	@Override
	public Optional<User> execute(User user)
	{
		UserValidation uv = new UserValidationImpl();
		AccountCredentialOperationsDao op = new AccountCredentialOperationsDao();
		LOGGER.info("\n\nDelete Account credential\n\nEnter App Name: ");
		String appName = input.nextLine();
		boolean isApp = false;
		
		for(UserAccount account : user.getAccounts())
		{
			if(op.isAppName(account, appName))
			{
				isApp = true;
				LOGGER.info("Application Found : "+ appName);
				LOGGER.info("\n\nEnter your (Master) password: ");
				String password = input.nextLine();
				if(uv.validatePassword(user, password))
					op.remove(user, account);
				else
					LOGGER.info("Incorrect Password...");
				break;
			}
		}
		if(!isApp)
			LOGGER.info("App not found...\n");
		
		return Optional.ofNullable(user);
	}

}
