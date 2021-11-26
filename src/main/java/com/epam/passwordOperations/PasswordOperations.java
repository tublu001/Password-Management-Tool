package com.epam.passwordOperations;

import com.epam.model.MasterUser;

public interface PasswordOperations 
{
	String generatePassword(MasterUser user);
	String encryptPassword(String strToEncrypt);
	String decryptPassword(String strToDecrypt);
}
