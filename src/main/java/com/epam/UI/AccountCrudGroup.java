package com.epam.UI;

import com.epam.model.MasterUsers;

public interface AccountCrudGroup 
{
	boolean createGroup(MasterUsers user);
	String storeInExistingGroup(MasterUsers user);
}
