package com.epam.repository;

import java.util.ArrayList;

import com.epam.model.User;

public class MasterUsersDB implements RepositoryDB
{
	private final ArrayList<User> users = new ArrayList<User>();


	@Override
	public Object getMasterUsers() {
		return null;
	}

	@Override
	public void setMasterUsers(Object obj) {

	}
}

