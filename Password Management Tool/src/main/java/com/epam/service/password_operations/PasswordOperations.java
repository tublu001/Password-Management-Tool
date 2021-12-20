package com.epam.service.password_operations;

import com.epam.model.User;

public interface PasswordOperations
{
    String generatePassword(User user);

    String encryptPassword(String stringToEncrypt);

    String decryptPassword(String stringToDecrypt);
}
