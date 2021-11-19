package com.epam.service;

import java.util.Optional;

import com.epam.model.MasterUsers;
import com.epam.passwordOperations.PreferredPassword;

public class SetPasswordPreference implements UserAccountCrudOperation {

	@Override
	public Optional<MasterUsers> execute(MasterUsers user) 
	{
		PreferredPassword pp = user.getPrefPass();
		pp.setPrefferdPassword();
		return Optional.ofNullable(user);
	}

}
