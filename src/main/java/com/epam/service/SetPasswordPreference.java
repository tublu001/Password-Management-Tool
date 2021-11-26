package com.epam.service;

import java.util.Optional;

import com.epam.model.MasterUser;
import com.epam.passwordOperations.PreferredPassword;

public class SetPasswordPreference implements UserAccountCrudOperation {

	@Override
	public Optional<MasterUser> execute(MasterUser user)
	{
		PreferredPassword pp = user.getPrefPass();
		pp.setPrefferdPassword();
		return Optional.ofNullable(user);
	}

}
