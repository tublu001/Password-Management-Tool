package com.epam.service;

import com.epam.exceptions.UserException;
import com.epam.user_interface.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountCrudFactory
{
    private static final Logger LOGGER = LogManager.getLogger(AccountCrudFactory.class);

    @Autowired
    private ApplicationContext context;

    public UserAccountCrudOperation getOperation(String selection) throws UserException
    {
        UserAccountCrudOperation tempObj = null;
        if (getObject(selection) != null)
        {
            tempObj = getObject(selection);
        } else if (selection.equals("0"))
        {
            LOGGER.info("Thank you... Signing Out...");
        } else
        {
            throw new UserException("Invalid Input! Try again...");
        }

        return tempObj;
    }

    public UserAccountCrudOperation getObject(String selection)
    {
        Map<String, UserAccountCrudOperation> operationObject = new HashMap<>();
        operationObject.put("1", context.getBean(AcquireAccountCredentials.class));
        operationObject.put("2", context.getBean(RetrieveAllAccounts.class));
        operationObject.put("3", context.getBean(RetrieveGroupWiseAllAccounts.class));
        operationObject.put("4", context.getBean(RetrieveAccountPassword.class));
        operationObject.put("5", context.getBean(RenameGroupName.class));
        operationObject.put("6", context.getBean(DeleteAccountCredential.class));
        operationObject.put("7", context.getBean(SetPasswordPreference.class));

        return operationObject.get(selection);
    }


}
