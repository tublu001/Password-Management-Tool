package com.epam.user_interface;

import com.epam.model.User;

import java.util.Optional;

public interface MasterCrudMenu 
{
	void createMaster();
	void showAllMasters();
	Optional<User> loginMaster();
}
