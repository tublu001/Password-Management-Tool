package com.epam.passwordOperations;

import com.epam.model.User;

public interface UserValidation {
    User validateMaster();
//    User getMasterUser(String userName);
    boolean validateUserName(String userName);
    boolean validatePassword(User user, String password);
}
