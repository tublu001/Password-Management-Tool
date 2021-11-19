package com.epam.service;

import java.util.Optional;

import com.epam.model.MasterUsers;

@FunctionalInterface
public interface UserAccountCrudOperation 
{
	Optional<MasterUsers> execute(MasterUsers user);
}
