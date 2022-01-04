package com.epam.Controller;

import com.epam.DTO.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.epam.utils.Constants.BOOKS_BASE_URL;

@FeignClient(name="Book", url = BOOKS_BASE_URL)
public interface BooksClient
{
    @GetMapping("books")
    public ResponseEntity<List<BookDto>> getBooks();
}
