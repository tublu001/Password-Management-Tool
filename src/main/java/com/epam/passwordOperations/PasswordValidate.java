package com.epam.passwordOperations;

import com.epam.model.MasterUsers;

public interface PasswordValidate 
{
	MasterUsers validateUser(); 
	boolean validatePassword(MasterUsers user); 
}
