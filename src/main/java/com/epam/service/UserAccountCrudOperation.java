package com.epam.service;

import java.util.Optional;

import com.epam.exceptions.UserException;
import com.epam.model.User;

@FunctionalInterface
public interface UserAccountCrudOperation 
{
	Optional<User> execute(User user) throws UserException;
}
