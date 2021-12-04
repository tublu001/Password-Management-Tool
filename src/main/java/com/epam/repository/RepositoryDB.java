package com.epam.repository;

import com.epam.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RepositoryDB
{
    List<User> getMasterUsers();
    boolean setMasterUser(User obj);
    boolean setMasterUsers(List<User> obj);
    boolean merge(User obj);
}
