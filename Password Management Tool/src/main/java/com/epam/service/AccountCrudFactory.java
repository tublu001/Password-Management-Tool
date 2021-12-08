package com.epam.service;

import com.epam.exceptions.UserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class AccountCrudFactory
{
    private static final Logger LOGGER = LogManager.getLogger(AccountCrudFactory.class);

    public UserAccountCrudOperation getOperation(String selection) throws UserException
    {
        UserAccountCrudOperation tempObj = null;
        if (getObject(selection) != null)
            tempObj = getObject(selection);
        else if (selection.equals("0"))
            LOGGER.info("Thank you... Signing Out...");
        else
            throw new UserException("Invalid Input! Try again...");

        return tempObj;
    }

    public UserAccountCrudOperation getObject(String selection)
    {
        Map<String, UserAccountCrudOperation> operationObject = new HashMap<>();
        operationObject.put("1", new AcquireAccountCredentials());
        operationObject.put("2", new RetriveAllAccounts());
        operationObject.put("3", new RetriveGroupWiseAllAccounts());
        operationObject.put("4", new RetriveAccountPassword());
        operationObject.put("5", new RenameGroupName());
        operationObject.put("6", new DeleteAccountCredential());
        operationObject.put("7", new SetPasswordPreference());

        return operationObject.get(selection);
    }


}
