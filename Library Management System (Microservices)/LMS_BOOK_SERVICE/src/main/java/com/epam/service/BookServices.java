package com.epam.service;

import com.epam.DTO.BookDto;
import com.epam.exceptions.UserException;
import com.epam.model.Book;

import java.util.List;

public interface BookServices
{
    List<Book> getBooks();

    Book getBookById(long id) throws UserException;

    List<Book> addBook(Book newBook) throws UserException;

    List<Book> deleteBook(long id) throws UserException;

    List<Book> updateBook(Long id, BookDto updatedBookDto) throws UserException;
}
