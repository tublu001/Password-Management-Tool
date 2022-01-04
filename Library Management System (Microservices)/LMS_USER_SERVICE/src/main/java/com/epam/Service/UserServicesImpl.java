package com.epam.Service;

import com.epam.DTO.UserDto;
import com.epam.Model.User;
import com.epam.Repository.UserRepository;
import com.epam.exceptions.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.utils.Constants.*;

@Service
public class UserServicesImpl implements UserServices
{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserName(String userName) throws UserException
    {
        return userRepository.findByUserName(userName).orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    @Override
    public List<User> addUser(User newUser) throws UserException
    {
        if (userRepository.existsByUserName(newUser.getUserName()))
        {
            throw new UserException(DUPLICATE_USER);
        }
        userRepository.save(newUser);
        return userRepository.findAll();
    }

    @Override
    public List<User> deleteUserByUserName(String userName) throws UserException
    {
        User existingUser = userRepository.findByUserName(userName).orElseThrow(() -> new UserException(USER_NOT_FOUND));
        userRepository.delete(existingUser);
        return userRepository.findAll();
    }

    @Override
    public List<User> updateUserByUserName(String userName, UserDto updatedUserDto) throws UserException
    {
        User existingUser = userRepository.findByUserName(userName).orElseThrow(() -> new UserException(USER_NOT_FOUND));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(updatedUserDto, existingUser);
        existingUser.setUserName(userName);
        userRepository.save(existingUser);
        return userRepository.findAll();
    }
}
