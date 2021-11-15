package com.epam.passwordOperations;

import com.epam.MasterGroups.MasterUsers;

public interface PasswordValidate 
{
	MasterUsers validateUser(); 
	boolean validatePassword(MasterUsers user); 
}
