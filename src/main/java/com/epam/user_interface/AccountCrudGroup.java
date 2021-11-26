package com.epam.user_interface;

import com.epam.model.MasterUser;

public interface AccountCrudGroup 
{
	boolean createGroup(MasterUser user);
	String storeInExistingGroup(MasterUser user);
}
