package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.UI.HomeMenu;
import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import com.epam.passwordOperations.UserValidate;
import com.epam.passwordOperations.UserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAccountCredential implements UserAccountCrudOperation 
{
	private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCredential.class);
	Scanner input = new Scanner(System.in);
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		UserValidation uv = new UserValidate();
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
				if(uv.validatePassword(user))
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
