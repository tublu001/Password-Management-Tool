package com.epam.AccountGroups;

import com.epam.MasterGroups.MasterUsers;

public interface AccountCrudGroup 
{
	boolean createGroup(MasterUsers user);
	String storeInExistingGroup(MasterUsers user);
}
