package com.epam.user_interface;

import java.util.Scanner;

import com.epam.model.MasterUser;
import com.epam.service.AccountCrudFactory;
import com.epam.service.UserAccountCrudOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountMenuCRUD {

	public AccountMenuCRUD() 
	{}
	private static final Logger LOGGER = LogManager.getLogger(AccountMenuCRUD.class);
	@SuppressWarnings(value = {"all"})
	public static void showCrudMenu(MasterUser user)
	{
		boolean repeatLoop = true;
		final Scanner input = new Scanner(System.in);
		
		while(repeatLoop)
		{
			LOGGER.info("\n\nChoose any of the Operation");
			LOGGER.info("1. Store a Account Credential");
			LOGGER.info("2. Retrive all Account details");
			LOGGER.info("3. Retrive Group wise Accounts details");
			LOGGER.info("4. Retrive any Account details");
			LOGGER.info("5. Rename any Group name");
			LOGGER.info("6. Delete any Account Credential");
			LOGGER.info("7. Set your Password preference");
			LOGGER.info("0. Sign Out\n\n\nChoose Any: ");
			
			char selection = input.next().charAt(0);
			
			UserAccountCrudOperation operations = new AccountCrudFactory().getOperation(selection);
			if(operations!=null)
				operations.execute(user);
			
			if(selection == '0')
				repeatLoop = false;
		}
		
	}

}
