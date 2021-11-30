package com.epam.service;

import java.util.Optional;

import com.epam.model.User;
import com.epam.passwordOperations.PreferredPassword;

public class SetPasswordPreference implements UserAccountCrudOperation {

	@Override
	public Optional<User> execute(User user)
	{
		PreferredPassword pp = user.getPrefPass();
		pp.setPrefferdPassword();
		return Optional.ofNullable(user);
	}

}
