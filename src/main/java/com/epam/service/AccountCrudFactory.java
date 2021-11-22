package com.epam.service;

import com.epam.UI.HomeMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountCrudFactory
{
	private static final Logger LOGGER = LogManager.getLogger(AccountCrudFactory.class);
	public UserAccountCrudOperation getOperation(char ch)
	{
		if(ch == '1')
			return new AcquireAccountCredentials();
		else if(ch == '2')
			return new RetriveAllAccounts();
		else if(ch == '3')
			return new RetriveGroupWiseAllAccounts();
		else if(ch == '4')
			return new RetriveAccountPassword();
		else if(ch == '5')
			return new RenameGroupName();
		else if(ch == '6')
			return new DeleteAccountCredential();
		else if(ch == '7')
			return new SetPasswordPreference();
		else if(ch == '0')
			LOGGER.info("Thank you... Signing Out...");
		else
			LOGGER.info("Invalid Input! Try again...");
		
		return null;
	}
}
