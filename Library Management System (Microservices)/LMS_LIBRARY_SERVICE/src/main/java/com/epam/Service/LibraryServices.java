package com.epam.Service;

import com.epam.DTO.LibraryDto;
import com.epam.DTO.UserDto;
import com.epam.DTO.UserLibraryRecordsDto;
import com.epam.Model.Library;
import com.epam.exceptions.UserException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LibraryServices
{
    UserLibraryRecordsDto getUserByUserName(String userName) throws UserException;

    List<UserLibraryRecordsDto> deleteUserByUserName(String userName) throws UserException;

    List<UserLibraryRecordsDto> issueBook(String userName, Long bookId) throws UserException;
    List<UserLibraryRecordsDto> releaseBook(String userName, Long bookId) throws UserException;
}
