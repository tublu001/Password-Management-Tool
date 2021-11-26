package com.epam.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class AccountCrudFactory
{
	private static final Logger LOGGER = LogManager.getLogger(AccountCrudFactory.class);
	public UserAccountCrudOperation getOperation(char ch)
	{
		UserAccountCrudOperation tempObj = null;
		if(getObject(ch) != null)
			tempObj = getObject(ch);
		else if(ch == '0')
			LOGGER.info("Thank you... Signing Out...");
		else
			LOGGER.info("Invalid Input! Try again...");
		
		return tempObj;
	}

	public UserAccountCrudOperation getObject(char selection)
	{
		Map<Character, UserAccountCrudOperation> operationObject = new HashMap<>();
		operationObject.put('1', new AcquireAccountCredentials());
		operationObject.put('2', new RetriveAllAccounts());
		operationObject.put('3', new RetriveGroupWiseAllAccounts());
		operationObject.put('4', new RetriveAccountPassword());
		operationObject.put('5', new RenameGroupName());
		operationObject.put('6', new DeleteAccountCredential());
		operationObject.put('7', new SetPasswordPreference());

		return operationObject.get(selection);
	}




}
