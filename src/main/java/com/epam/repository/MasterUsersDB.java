package com.epam.repository;

import java.util.ArrayList;
import java.util.List;

import com.epam.model.User;

//public class MasterUsersDB implements RepositoryDB
public class MasterUsersDB
{
	private final ArrayList<User> users = new ArrayList<User>();

//	@Override
	public List<User> getMasterUsers() {
		return null;
	}

//	@Override
	public boolean setMasterUsers(List<User> obj) {
		return false;
	}
//	@Override
	public boolean setMasterUser(User user) {
		return false;
	}
}

