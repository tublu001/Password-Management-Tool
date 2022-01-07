package com.epam.Service;

import com.epam.DTO.BookDto;
import com.epam.DTO.UserDto;
import com.epam.DTO.UserLibraryRecordsDto;
import com.epam.Model.Library;
import com.epam.Repository.LibraryRepository;
import com.epam.exceptions.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServicesImpl implements LibraryServices
{
    @Autowired
    BooksClient booksClient;
    @Autowired
    UsersClient usersClient;
    @Autowired
    LibraryRepository libraryRepository;

    @Override
    public UserLibraryRecordsDto getUserByUserName(String userName) throws UserException
    {
        return getUserLibraryRecordsDto( usersClient.getUser(userName).getBody());
    }

    @Override
    public List<UserLibraryRecordsDto> deleteUserByUserName(String userName) throws UserException
    {
        libraryRepository.deleteAll(libraryRepository.findByUserName(userName));
        usersClient.deleteUser(userName);
        return defaulterUsers();
    }

    @Override
    public List<UserLibraryRecordsDto> issueBook(String userName, Long bookId) throws UserException
    {
        BookDto book =  booksClient.getBook(bookId).getBody();
        UserDto user =  usersClient.getUser(userName).getBody();
        if (libraryRepository.findByUserNameAndBookId(userName, bookId).isPresent())
        {
            throw new UserException("Book already issued by " + userName);
        }
        if(libraryRepository.findByUserName(userName).size()>=3)
        {
            throw new UserException("Maximum number of books are issued for " + userName);
        }
        Library newRecord = new Library(userName, bookId);
        libraryRepository.save(newRecord);
        return defaulterUsers();
    }

    @Override
    public List<UserLibraryRecordsDto> releaseBook(String userName, Long bookId) throws UserException
    {
        Library existingRecord = libraryRepository.findByUserNameAndBookId(userName, bookId).orElseThrow(() -> new UserException("Record Not Found"));
        System.out.println(existingRecord);
        libraryRepository.delete(existingRecord);
        libraryRepository.findAll();
        return defaulterUsers();
    }

    public List<UserLibraryRecordsDto> defaulterUsers() throws UserException
    {
        ModelMapper modelMapper = new ModelMapper();
        List<UserLibraryRecordsDto> userLibraryRecordsDtoList = new ArrayList<>();

        usersClient.getUsers().getBody().forEach(user ->
        {
            if (!libraryRepository.findByUserName(user.getUserName()).isEmpty())
            {
                UserLibraryRecordsDto newRecord = getUserLibraryRecordsDto(user);
                userLibraryRecordsDtoList.add(newRecord);
            }
        });
        return userLibraryRecordsDtoList;
    }

    private UserLibraryRecordsDto getUserLibraryRecordsDto(UserDto user)
    {
        UserLibraryRecordsDto newRecord = new UserLibraryRecordsDto();
        UserDto userDto = null;
        try
        {
            userDto = usersClient.getUser(user.getUserName()).getBody();
        }catch(Exception e){e.printStackTrace();}

        newRecord.setName(userDto.getName());
        newRecord.setUserName(userDto.getUserName());
        newRecord.setUserEmail(userDto.getUserEmail());
        libraryRepository.findByUserName(user.getUserName()).forEach(records ->
                {
                    try
                    {
                        newRecord.getIssuedBooks().add(booksClient.getBook(records.getBookId()).getBody());
                    }catch (Exception e){e.printStackTrace();}
                }
        );
        return newRecord;
    }
}
