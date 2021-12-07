package com.epam.passwordOperations;

import com.epam.model.User;

import java.util.Optional;

public interface UserValidation {
    Optional<User> validateMaster();
    boolean validateUserName(String userName);
    boolean validatePassword(User user, String password);
}
