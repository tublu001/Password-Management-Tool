package com.epam.user_interface;

import com.epam.exceptions.UserException;
import com.epam.model.User;

public interface AccountCrudGroup 
{
	boolean createGroup(User user) throws UserException;
	String storeInExistingGroup(User user) throws UserException;
}
