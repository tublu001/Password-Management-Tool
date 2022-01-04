package com.epam.Controller;

import com.epam.DTO.UserDto;
import com.epam.DTO.UserLibraryRecordsDto;
import com.epam.Service.LibraryServices;
import com.epam.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController
{
    @Autowired
    LibraryServices libraryServices;

    @GetMapping("users/{userName}")
    public ResponseEntity<UserLibraryRecordsDto> getUser(@PathVariable String userName) throws UserException
    {
        return new ResponseEntity<>(libraryServices.getUserByUserName(userName), HttpStatus.OK);
    }

    @DeleteMapping("users/{userName}")
    public ResponseEntity<List<UserLibraryRecordsDto>> deleteUser(@PathVariable String userName) throws UserException
    {
        return new ResponseEntity<>(libraryServices.deleteUserByUserName(userName), HttpStatus.NO_CONTENT);
    }

    @PostMapping("users/{userName}/books/{bookId}")
    public ResponseEntity<List<UserLibraryRecordsDto>> issueBook(@PathVariable String userName, @PathVariable Long bookId) throws UserException
    {
        return new ResponseEntity<>(libraryServices.issueBook(userName, bookId),HttpStatus.CREATED);
    }

    @DeleteMapping("users/{userName}/books/{bookId}")
    public ResponseEntity<List<UserLibraryRecordsDto>> releaseBook(@PathVariable String userName, @PathVariable Long bookId) throws UserException
    {
        return new ResponseEntity<>(libraryServices.releaseBook(userName, bookId),HttpStatus.NO_CONTENT);
    }
}
