package com.epam.service;

import com.epam.DTO.BookDto;
import com.epam.exceptions.UserException;
import com.epam.model.Book;
import com.epam.repository.BookRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookServicesImplTest
{

    @InjectMocks
    BookServicesImpl bookService;
    @Mock
    BookRepository bookRepository;
    @Mock
    ModelMapper modelMapper;
    BookDto bookDto;
    Book book;

    @BeforeEach
    public void setUp()
    {
        bookDto = new BookDto("ram", "Ayodhya", "RamTiwari");
        book = new Book("ram", "Ayodhya", "RamTiwari");
    }

    @Test
    void testGetAllBooks()
    {
        when(bookRepository.findAll()).thenReturn(List.of(book, book));
        assertEquals(List.of(book, book), bookService.getBooks());
    }

    @Test
    void testGetBookById() throws UserException
    {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        assertEquals(book, bookService.getBookById(1));
    }

    @Test
    public void getBookShouldThrowExceptionIfBookNotExist()
    {
        when(bookRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());
        assertThrows(UserException.class, () -> bookService.getBookById(1));
    }

    @Test
    void testSaveBook()
    {
        assertDoesNotThrow(() -> bookService.addBook(book));
    }

    @Test
    public void saveBookShouldThrowExceptionIfBookAlreadyExist()
    {
        when(bookRepository.existsById(anyLong())).thenReturn(true);
        assertThrows(UserException.class, () -> bookService.addBook(book));
    }

    @Test
    void testUpdateBook()
    {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        assertDoesNotThrow(() -> bookService.updateBook(bookDto.getBookId(), bookDto));
    }

    @Test
    void updateBookShouldThrowExceptionIfBookNotExist()
    {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserException.class, () -> bookService.updateBook(bookDto.getBookId(), bookDto));
    }

    @Test
    void testDeleteBook()
    {
        when(bookRepository.existsById(anyLong())).thenReturn(true);
        assertDoesNotThrow(() -> bookService.deleteBook(book.getBookId()));
    }

    @Test
    void deleteBookShouldThrowExceptionIfBookNotExist()
    {
        when(bookRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(UserException.class, () -> bookService.deleteBook(book.getBookId()));
    }

}