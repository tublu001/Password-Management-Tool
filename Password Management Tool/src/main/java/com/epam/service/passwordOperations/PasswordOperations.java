package com.epam.service.passwordOperations;

import com.epam.model.User;

public interface PasswordOperations
{
    String generatePassword(User user);

    String encryptPassword(String stringToEncrypt);

    String decryptPassword(String stringToDecrypt);
}
