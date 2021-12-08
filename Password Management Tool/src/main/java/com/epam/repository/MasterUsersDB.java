package com.epam.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.model.User;

public class MasterUsersDB implements RepositoryDB
{
	private final ArrayList<User> users = new ArrayList<>();

	@Override
	public List<User> getMasterUsers() {
		return users;
	}

	@Override
	public Optional<User> setMasterUser(User obj) {
		return Optional.empty();
	}

//	@Override
	public boolean setMasterUsers(List<User> obj) {
		return false;
	}

	@Override
	public Optional<User> merge(User obj) {
		return Optional.empty();
	}
}

