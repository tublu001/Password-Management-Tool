package com.epam.service;

import java.util.Optional;

import com.epam.dao.GroupOperationsDao;
import com.epam.model.User;

public class RetriveGroupWiseAllAccounts implements UserAccountCrudOperation 
{
	GroupOperationsDao goperate = new GroupOperationsDao();
	@Override
	public Optional<User> execute(User user)
	{
		if(user != null) {
			goperate.getGroupWiseAccounts(user);
			return Optional.ofNullable(user);
		}
		else
			return Optional.empty();
	}

}
