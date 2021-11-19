package com.epam.service;

import java.util.Optional;

import com.epam.dao.AccountCredentialOperationsDao;
import com.epam.model.MasterUsers;
import com.epam.model.UserAccount;

public class RetriveAllAccounts implements UserAccountCrudOperation {

	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		System.out.print("\n\nAll Account Details\n\n");
		int sl = 1;
		for(UserAccount account : user.getAccounts())
		{
			System.out.print(sl++ + ". ");
			new AccountCredentialOperationsDao().showAccount(account);
		}
		return Optional.ofNullable(user);
	}

}
