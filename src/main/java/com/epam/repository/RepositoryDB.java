package com.epam.repository;

import com.epam.model.User;

import java.util.ArrayList;

public interface RepositoryDB
{
    Object getMasterUsers();
    void setMasterUsers(Object obj);
}
