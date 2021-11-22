package com.epam.passwordOperations;

import com.epam.model.MasterUsers;

public interface UserValidation {
    MasterUsers validateUser();
    boolean validatePassword(MasterUsers user);
}
