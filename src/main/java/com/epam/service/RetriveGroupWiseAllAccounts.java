package com.epam.service;

import java.util.Optional;

import com.epam.dao.GroupOperationsDao;
import com.epam.model.MasterUsers;

public class RetriveGroupWiseAllAccounts implements UserAccountCrudOperation 
{
	GroupOperationsDao goperate = new GroupOperationsDao();
	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		goperate.getGroupWiseAccounts(user);
		return Optional.ofNullable(user);
	}

}
