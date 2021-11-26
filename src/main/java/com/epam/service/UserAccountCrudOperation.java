package com.epam.service;

import java.util.Optional;

import com.epam.model.MasterUser;

@FunctionalInterface
public interface UserAccountCrudOperation 
{
	Optional<MasterUser> execute(MasterUser user);
}
