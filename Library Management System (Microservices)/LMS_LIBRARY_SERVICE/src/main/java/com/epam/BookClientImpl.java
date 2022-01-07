package com.epam;

import com.epam.DTO.BookDto;
import com.epam.Service.BooksClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BookClientImpl implements BooksClient
{
    @Override
    public ResponseEntity<List<BookDto>> getBooks()
    {
        List<BookDto> bookDto = new ArrayList<BookDto>((Collection<? extends BookDto>) new BookDto("", "", ""));
        return new ResponseEntity<>(bookDto, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<BookDto> getBook(Long id)
    {
        return null;
    }

    @Override
    public ResponseEntity<List<BookDto>> deleteBook(Long id)
    {
        return null;
    }

    @Override
    public ResponseEntity<List<BookDto>> storeBooks(BookDto bookDto)
    {
        return null;
    }

    @Override
    public ResponseEntity<List<BookDto>> updateBooks(Long id, BookDto updatedBookDto)
    {
        return null;
    }
}
