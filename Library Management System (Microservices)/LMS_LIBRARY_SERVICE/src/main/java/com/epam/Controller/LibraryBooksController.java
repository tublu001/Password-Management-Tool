package com.epam.Controller;

import com.epam.DTO.BookDto;
import com.epam.Service.BookServices;
import com.epam.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryBooksController
{
    @Autowired
    BookServices bookServices;

    @GetMapping("books")
    public ResponseEntity<List<BookDto>> getBooks()
    {
        return bookServices.getBooks();
    }

    @GetMapping("books/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) throws UserException
    {
        return bookServices.getBookById(id);
    }


    @DeleteMapping("books/{id}")
    public ResponseEntity<List<BookDto>> deleteBook(@PathVariable Long id) throws UserException
    {
        return bookServices.deleteBook(id);
    }

    @PostMapping("books")
    public ResponseEntity<List<BookDto>> storeBooks(@RequestBody BookDto bookDto) throws UserException
    {
        return bookServices.addBook(bookDto);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<List<BookDto>> updateBooks(@PathVariable Long id, @RequestBody BookDto updatedBookDto) throws UserException
    {
        return bookServices.updateBook(id, updatedBookDto);
    }
}
