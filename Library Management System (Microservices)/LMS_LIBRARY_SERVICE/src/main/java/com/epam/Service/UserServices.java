package com.epam.Service;

import com.epam.DTO.UserDto;
import com.epam.exceptions.UserException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServices
{
    ResponseEntity<List<UserDto>> getUsers();
    ResponseEntity<UserDto> getUserByUserName(String userName) throws UserException;
    ResponseEntity<List<UserDto>> addUser(UserDto newUserDto);
    ResponseEntity<List<UserDto>> deleteUserByUserName(String userName) throws UserException;
    ResponseEntity<List<UserDto>> updateUserByUserName(String userName, UserDto updatedUserDto) throws UserException;
}
