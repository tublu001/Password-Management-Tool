package com.epam.passwordOperations;

import com.epam.model.User;

public interface PasswordOperations
{
    String generatePassword(User user);

    String encryptPassword(String strToEncrypt);

    String decryptPassword(String strToDecrypt);
}
