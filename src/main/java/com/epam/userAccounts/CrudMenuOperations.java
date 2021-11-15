package com.epam.userAccounts;

import com.epam.MasterGroups.MasterUsers;

public interface CrudMenuOperations 
{
	MasterUsers acquireAccountCredentials(MasterUsers user);
	void retriveAccountCredential(MasterUsers user);
	MasterUsers deleteAccountCredential(MasterUsers user);
	void retriveAllAccounts(MasterUsers user);
	void setPasswordPreference(MasterUsers user);
}
