package com.epam.repository;

import java.util.ArrayList;
import java.util.List;

import com.epam.model.User;

public class MasterUsersDB implements RepositoryDB
{
	private final ArrayList<User> users = new ArrayList<>();

	@Override
	public List<User> getMasterUsers() {
		return users;
	}

	@Override
	public boolean setMasterUser(User obj) {
		return false;
	}

	@Override
	public boolean setMasterUsers(List<User> obj) {
		return false;
	}

	@Override
	public boolean merge(User obj) {
		return false;
	}



}

