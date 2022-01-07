package com.epam.Controller;

import com.epam.DTO.BookDto;
import com.epam.Service.BookServices;
import com.epam.Service.BooksClient;
import com.epam.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryBooksController
{
    @Autowired
    BooksClient booksClient;

    @GetMapping("books")
    public ResponseEntity<List<BookDto>> getBooks()
    {
        return booksClient.getBooks();
    }

    @GetMapping("books/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) throws UserException
    {
        return booksClient.getBook(id);
    }


    @DeleteMapping("books/{id}")
    public ResponseEntity<List<BookDto>> deleteBook(@PathVariable Long id) throws UserException
    {
        return booksClient.deleteBook(id);
    }

    @PostMapping("books")
    public ResponseEntity<List<BookDto>> storeBooks(@RequestBody BookDto bookDto) throws UserException
    {
        return booksClient.storeBooks(bookDto);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<List<BookDto>> updateBooks(@PathVariable Long id, @RequestBody BookDto updatedBookDto) throws UserException
    {
        return booksClient.updateBooks(id, updatedBookDto);
    }
}
