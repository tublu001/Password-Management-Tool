package com.epam.Service;

import com.epam.DTO.BookDto;
import com.epam.exceptions.UserException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookServices
{
    ResponseEntity<List<BookDto>> getBooks();
    ResponseEntity<BookDto> getBookById(long id) throws UserException;
    ResponseEntity<List<BookDto>> addBook(BookDto newBookDto);
    ResponseEntity<List<BookDto>> deleteBook(long id) throws UserException;
    ResponseEntity<List<BookDto>> updateBook(Long id, BookDto updatedBookDto) throws UserException;
}
