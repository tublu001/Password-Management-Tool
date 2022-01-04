package com.epam.Service;

import com.epam.DTO.UserDto;
import com.epam.Model.User;
import com.epam.exceptions.UserException;

import java.util.List;

public interface UserServices
{
    List<User> getUsers();
    User getUserByUserName(String userName) throws UserException;
    List<User> addUser(User newUser) throws UserException;
    List<User> deleteUserByUserName(String userName) throws UserException;
    List<User> updateUserByUserName(String userName, UserDto updatedUserDto) throws UserException;
}
