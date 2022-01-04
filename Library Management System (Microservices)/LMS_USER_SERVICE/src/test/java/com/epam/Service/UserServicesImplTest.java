package com.epam.Service;

import com.epam.DTO.UserDto;
import com.epam.Model.User;
import com.epam.Repository.UserRepository;
import com.epam.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServicesImplTest
{

    @InjectMocks
    UserServicesImpl UserService;
    @Mock
    UserRepository UserRepository;
    @Mock
    ModelMapper modelMapper;
    UserDto UserDto;
    User User;

    @BeforeEach
    public void setUp()
    {
        UserDto = new UserDto("ram", "qwerty@qwerty.com", "RamTiwari");
        User = new User("ram", "qwerty@qwerty.com", "RamTiwari");
    }

    @Test
    void testGetAllUsers()
    {
        when(UserRepository.findAll()).thenReturn(List.of(User, User));
        assertEquals(List.of(User, User), UserService.getUsers());
    }

    @Test
    void testGetUserById() throws UserException
    {
        when(UserRepository.findByUserName(anyString())).thenReturn(Optional.of(User));
        assertEquals(User, UserService.getUserByUserName("ram"));
    }

    @Test
    public void getUserShouldThrowExceptionIfUserNotExist()
    {
        when(UserRepository.findByUserName(anyString())).thenReturn(java.util.Optional.empty());
        assertThrows(UserException.class, () -> UserService.getUserByUserName("ram"));
    }

    @Test
    void testSaveUser()
    {
        assertDoesNotThrow(() -> UserService.addUser(User));
    }

    @Test
    public void saveUserShouldThrowExceptionIfUserAlreadyExist()
    {
        when(UserRepository.existsByUserName(anyString())).thenReturn(true);
        assertThrows(UserException.class, () -> UserService.addUser(User));
    }

    @Test
    void testUpdateUser()
    {
        when(UserRepository.findByUserName(anyString())).thenReturn(Optional.of(User));
        when(UserRepository.save(any(User.class))).thenReturn(User);
        assertDoesNotThrow(() -> UserService.updateUserByUserName(UserDto.getUserName(), UserDto));
    }

    @Test
    void updateUserShouldThrowExceptionIfUserNotExist()
    {
        when(UserRepository.findByUserName(anyString())).thenReturn(Optional.empty());
        assertThrows(UserException.class, () -> UserService.updateUserByUserName(UserDto.getUserName(), UserDto));
    }

    @Test
    void testDeleteUser()
    {
        when(UserRepository.findByUserName(anyString())).thenReturn(Optional.of(User));
        assertDoesNotThrow(() -> UserService.deleteUserByUserName(User.getUserName()));
    }

    @Test
    void deleteUserShouldThrowExceptionIfUserNotExist()
    {
        when(UserRepository.findByUserName(anyString())).thenReturn(Optional.empty());
        assertThrows(UserException.class, () -> UserService.deleteUserByUserName(User.getUserName()));
    }
}