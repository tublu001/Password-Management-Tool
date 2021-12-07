package com.epam.user_interface;

import com.epam.exceptions.UserException;
import com.epam.model.User;

import java.util.Optional;

public interface MasterCrudMenu 
{
	void createMaster() throws UserException;
	void showAllMasters();
	Optional<User> loginMaster() throws UserException;
}
