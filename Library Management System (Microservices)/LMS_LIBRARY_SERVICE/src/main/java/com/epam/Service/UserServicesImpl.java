package com.epam.Service;

import com.epam.DTO.BookDto;
import com.epam.DTO.UserDto;
import com.epam.Model.User;
import com.epam.exceptions.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.epam.utils.Constants.USERS_BASE_URL;

@Service
public class UserServicesImpl implements UserServices
{
    @Autowired
    RestTemplate restTemplate;

    HttpEntity<?> entity = null;

    @Override
    public ResponseEntity<List<UserDto>> getUsers()
    {
        return restTemplate.exchange(USERS_BASE_URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserDto>>(){});
    }

    @Override
    public ResponseEntity<UserDto> getUserByUserName(String userName) throws UserException
    {
        return restTemplate.getForEntity(USERS_BASE_URL+userName, UserDto.class);
    }

    @Override
    public ResponseEntity<List<UserDto>> addUser(UserDto newUser)
    {
        return restTemplate.exchange(USERS_BASE_URL, HttpMethod.POST, entity, new ParameterizedTypeReference<List<UserDto>>(){});
    }

    @Override
    public ResponseEntity<List<UserDto>> deleteUserByUserName(String userName) throws UserException
    {
        return restTemplate.exchange(USERS_BASE_URL+userName, HttpMethod.DELETE, entity, new ParameterizedTypeReference<List<UserDto>>(){});
    }

    @Override
    public ResponseEntity<List<UserDto>> updateUserByUserName(String userName, UserDto updatedUserDto) throws UserException
    {
        return restTemplate.exchange(USERS_BASE_URL+userName, HttpMethod.PUT, entity, new ParameterizedTypeReference<List<UserDto>>(){});
    }
}
