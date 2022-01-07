package com.epam.Service;

import com.epam.DTO.BookDto;
import com.epam.DTO.UserDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.utils.Constants.BOOKS_BASE_URL;
import static com.epam.utils.Constants.USERS_BASE_URL;

@FeignClient(name="user-service")
@LoadBalancerClient(name="user-service")
public interface UsersClient
{
    @GetMapping("/users")
    ResponseEntity<List<UserDto>> getUsers();

    @GetMapping("/users/{userName}")
    ResponseEntity<UserDto> getUser(@PathVariable String userName);

    @DeleteMapping("/users/{userName}")
    ResponseEntity<List<UserDto>> deleteUser(@PathVariable String userName);

    @PostMapping
    ResponseEntity<List<UserDto>> storeUsers(@RequestBody UserDto UserDto);

    @PutMapping("/users/{userName}")
    ResponseEntity<List<UserDto>> updateUsers(@PathVariable String userName, @RequestBody UserDto updatedUserDto);
}
