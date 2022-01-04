package com.epam.service;

import com.epam.DTO.BookDto;
import com.epam.exceptions.UserException;
import com.epam.model.Book;
import com.epam.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.utils.Constants.*;

@Service
public class BookServicesImpl implements BookServices
{
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getBooks()
    {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(long id) throws UserException
    {
        return bookRepository.findById(id).orElseThrow(() -> new UserException(BOOK_NOT_FOUND));
    }

    @Override
    public List<Book> addBook(Book newBook) throws UserException
    {
        if (bookRepository.existsById(newBook.getBookId()) || bookRepository.existsByBookName(newBook.getBookName()))
        {
            throw new UserException(DUPLICATE_BOOK);
        }
        bookRepository.save(newBook);
        return bookRepository.findAll();
    }

    @Override
    public List<Book> deleteBook(long id) throws UserException
    {
        if (!bookRepository.existsById(id))
        {
            throw new UserException(BOOK_NOT_FOUND);
        }
        bookRepository.deleteById(id);
        return bookRepository.findAll();
    }

    @Override
    public List<Book> updateBook(Long id, BookDto updatedBookDto) throws UserException
    {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new UserException(BOOK_NOT_FOUND));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(updatedBookDto, existingBook);
        existingBook.setBookId(id);
        bookRepository.save(existingBook);
        return bookRepository.findAll();
    }
}
