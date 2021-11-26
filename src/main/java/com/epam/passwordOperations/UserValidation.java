package com.epam.passwordOperations;

import com.epam.model.MasterUser;

public interface UserValidation {
    MasterUser validateMaster();
    MasterUser getMasterUser(String userName);
    boolean validateUserName(String userName);
    boolean validatePassword(MasterUser user, String password);
}
