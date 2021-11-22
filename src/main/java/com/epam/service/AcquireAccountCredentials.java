package com.epam.service;

import java.util.Optional;
import java.util.Scanner;

import com.epam.UI.GroupMenu;
import com.epam.UI.HomeMenu;
import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PwdOperate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AcquireAccountCredentials implements UserAccountCrudOperation 
{
	private static final Logger LOGGER = LogManager.getLogger(AcquireAccountCredentials.class);
	Scanner input = new Scanner(System.in);
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		LOGGER.info("\n\nStore Account credentials\n\nEnter App Name: ");
		String appName = input.nextLine();
		if(!new AccountCredentialOperationsDao().isAppPresent(user, appName))
		{
			LOGGER.info("Enter URL: ");
			String url = input.nextLine();
			LOGGER.info("Press enter to generate a new password for ("+appName+")..  ");
			input.nextLine();
			
			//Password generation and encryption
			PasswordOperations operate = new PwdOperate();
	        String pwd = operate.generatePassword(user);
	        String encPwd = operate.encryptPassword(pwd);
	        
	        LOGGER.info("\n\nPassword generated as per your preference. Copy this password and use it in your application:\n" + pwd);
	        LOGGER.info("\nPress enter for setting up Group\n");
	        input.nextLine();
	        String groupName = GroupMenu.showGroupUI(user);
			user = new AccountCredentialOperationsDao().store(user, appName, url, encPwd, groupName);
			return Optional.ofNullable(user);
		}
		LOGGER.info("App already present in Database... Try again..\n");
		return Optional.ofNullable(user);
	}

}
