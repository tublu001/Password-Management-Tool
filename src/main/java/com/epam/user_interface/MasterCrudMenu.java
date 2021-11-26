package com.epam.user_interface;

import com.epam.model.MasterUser;

public interface MasterCrudMenu 
{
	void createMaster();
	void showAllMasters();
	MasterUser loginMaster();
}
