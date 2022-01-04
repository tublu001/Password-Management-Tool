package com.epam.Controller;

import com.epam.DTO.UserDto;
import com.epam.Model.User;
import com.epam.Service.UserServices;
import com.epam.exceptions.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    UserServices userServices;

    @GetMapping
    public ResponseEntity<List<User>> getUsers()
    {
        return new ResponseEntity<>(userServices.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@Valid @PathVariable String userName) throws UserException
    {
        return new ResponseEntity<>(userServices.getUserByUserName(userName), HttpStatus.OK);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<List<User>> deleteUser(@Valid @PathVariable String userName) throws UserException
    {
        return new ResponseEntity<>(userServices.deleteUserByUserName(userName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<User>> storeUsers(@Valid @RequestBody UserDto UserDto) throws UserException
    {
        User newUser = new User();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(UserDto, newUser);
        return new ResponseEntity<>(userServices.addUser(newUser), HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<List<User>> updateUsers(@Valid @PathVariable String userName, @Valid @RequestBody UserDto updatedUserDto) throws UserException
    {
        return new ResponseEntity<>(userServices.updateUserByUserName(userName, updatedUserDto), HttpStatus.OK);
    }


}
