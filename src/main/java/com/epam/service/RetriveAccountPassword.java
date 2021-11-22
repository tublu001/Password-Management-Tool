package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.UI.HomeMenu;
import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetriveAccountPassword implements UserAccountCrudOperation 
{
	private static final Logger LOGGER = LogManager.getLogger(RetriveAccountPassword.class);
	Scanner input = new Scanner(System.in);
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		LOGGER.info("\n\nRetrive Account password\n\nEnter App Name: ");
		String appName = input.nextLine();
		AccountCredentialOperationsDao op = new AccountCredentialOperationsDao();
		boolean isApp = false;
		
		for(UserAccount account : user.getAccounts())
		{
			if(op.isAppName(account, appName))
			{
				isApp = true;
				LOGGER.info("Account password is: " + op.retrivePassword(account));
				break;
			}
		}
		if(!isApp)
			LOGGER.info("App not found...\n");
		
		return Optional.of(user);
	}

}
