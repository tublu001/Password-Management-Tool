package com.epam.user_interface;

import com.epam.model.User;

public interface AccountCrudGroup 
{
	boolean createGroup(User user);
	String storeInExistingGroup(User user);
}
