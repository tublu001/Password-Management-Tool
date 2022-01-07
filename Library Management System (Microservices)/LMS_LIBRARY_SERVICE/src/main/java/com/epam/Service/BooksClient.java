package com.epam.Service;

import com.epam.DTO.BookDto;
import com.epam.BookClientImpl;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="book-service", fallback = BookClientImpl.class)
@LoadBalancerClient(name="book-service", configuration = BookClientImpl.class)
public interface BooksClient
{
    @GetMapping("/books")
    ResponseEntity<List<BookDto>> getBooks();

    @GetMapping("/books/{id}")
    ResponseEntity<BookDto> getBook(@PathVariable Long id);

    @DeleteMapping("/books/{id}")
    ResponseEntity<List<BookDto>> deleteBook(@PathVariable Long id);

    @PostMapping
    ResponseEntity<List<BookDto>> storeBooks(@RequestBody BookDto bookDto);

    @PutMapping("/books/{id}")
    ResponseEntity<List<BookDto>> updateBooks(@PathVariable Long id, @RequestBody BookDto updatedBookDto);
}
