package com.epam.Controller;

import com.epam.DTO.UserDto;
import com.epam.Service.UserServices;
import com.epam.Service.UsersClient;
import com.epam.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryUsersController
{
    @Autowired
    UserServices UserServices;

    @Autowired
    UsersClient usersClient;

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers()
    {
        return usersClient.getUsers();
    }

//    @GetMapping("users/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userName) throws UserException
    {
        return UserServices.getUserByUserName(userName);
    }

//    @DeleteMapping("users/{userName}")
    public ResponseEntity<List<UserDto>> deleteUser(@PathVariable String userName) throws UserException
    {
        return UserServices.deleteUserByUserName(userName);
    }

    @PostMapping("users")
    public ResponseEntity<List<UserDto>> storeUsers(@RequestBody UserDto UserDto) throws UserException
    {
        return usersClient.storeUsers(UserDto);
    }

    @PutMapping("users/{userName}")
    public ResponseEntity<List<UserDto>> updateUsers(@PathVariable String userName, @RequestBody UserDto updatedUserDto) throws UserException
    {
        return usersClient.updateUsers(userName, updatedUserDto);
    }
}
