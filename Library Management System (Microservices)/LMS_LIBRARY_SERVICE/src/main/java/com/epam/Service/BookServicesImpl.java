package com.epam.Service;

import com.epam.DTO.BookDto;
import com.epam.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.epam.utils.Constants.BOOKS_BASE_URL;

@Service
public class BookServicesImpl implements BookServices
{
    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<List<BookDto>> getBooks()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange(BOOKS_BASE_URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<BookDto>>(){});
    }

    @Override
    public ResponseEntity<BookDto> getBookById(long id) throws UserException
    {
//        return restTemplate.getForEntity(BOOKS_BASE_URL+id, BookDto.class);
        return restTemplate.getForEntity(BOOKS_BASE_URL+id, BookDto.class);

    }

    @Override
    public ResponseEntity<List<BookDto>> addBook(BookDto newBookDto)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BookDto> entity = new HttpEntity<BookDto>(newBookDto, headers);
        return restTemplate.exchange(BOOKS_BASE_URL, HttpMethod.POST, entity, new ParameterizedTypeReference<List<BookDto>>(){});
    }

    @Override
    public ResponseEntity<List<BookDto>> deleteBook(long id) throws UserException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange(BOOKS_BASE_URL+id, HttpMethod.DELETE, entity, new ParameterizedTypeReference<List<BookDto>>(){});
    }

    @Override
    public ResponseEntity<List<BookDto>> updateBook(Long id, BookDto updatedBookDto) throws UserException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BookDto> entity = new HttpEntity<BookDto>(updatedBookDto, headers);
        return restTemplate.exchange(BOOKS_BASE_URL+id, HttpMethod.PUT, entity, new ParameterizedTypeReference<List<BookDto>>(){});
    }
}
