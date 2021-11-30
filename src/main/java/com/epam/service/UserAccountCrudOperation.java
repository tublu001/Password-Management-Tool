package com.epam.service;

import java.util.Optional;

import com.epam.model.User;

@FunctionalInterface
public interface UserAccountCrudOperation 
{
	Optional<User> execute(User user);
}
