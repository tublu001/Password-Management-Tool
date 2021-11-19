package com.epam.passwordOperations;

import com.epam.model.MasterUsers;

public interface PasswordOperations 
{
	String generatePassword(MasterUsers user);
	String encryptPassword(String strToEncrypt);
	String decryptPassword(String strToDecrypt);
}
