package com.epam.controller;

import com.epam.DTO.BookDto;
import com.epam.exceptions.UserException;
import com.epam.model.Book;
import com.epam.service.BookServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.epam.utils.Constants.DUPLICATE_BOOK;

@RestController
@RequestMapping("/books")
public class BookController
{

    @Autowired
    BookServices bookServices;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks()
    {
        return new ResponseEntity<>(bookServices.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) throws UserException
    {
        return new ResponseEntity<>(bookServices.getBookById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Book>> deleteBook(@PathVariable Long id) throws UserException
    {
        return new ResponseEntity<>(bookServices.deleteBook(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Book>> storeBooks(@Valid @RequestBody BookDto bookDto) throws UserException
    {
        Book newBook = new Book();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(bookDto, newBook);
        return new ResponseEntity<>(bookServices.addBook(newBook), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Book>> updateBooks(@PathVariable Long id, @Valid @RequestBody BookDto updatedBookDto) throws UserException
    {
        return new ResponseEntity<>(bookServices.updateBook(id, updatedBookDto), HttpStatus.OK);
    }


}
