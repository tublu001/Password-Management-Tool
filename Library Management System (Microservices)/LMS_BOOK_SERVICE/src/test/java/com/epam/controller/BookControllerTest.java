package com.epam.controller;

import com.epam.DTO.BookDto;
import com.epam.exceptions.UserException;
import com.epam.service.BookServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookController.class)
@AutoConfigureMockMvc
class BookControllerTest
{

    @MockBean
    BookServices bookService;
    @Autowired
    MockMvc mockMvc;


    @Test
    void testBookById() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testAllBooks() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testBookByIdIfBookNotExist() throws Exception
    {
        doThrow(UserException.class).when(bookService).getBookById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveBook() throws Exception
    {
        BookDto bookDto = new BookDto("ram", "Ayodhya", "RamTiwari");
        mockMvc.perform(MockMvcRequestBuilders.post("/books/").content(asJsonString(bookDto))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnsupportedMediaType());

    }


    @Test
    void testUpdateBook() throws Exception
    {
        BookDto bookDto = new BookDto("ram", "Ayodhya", "RamTiwari");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/books/1")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void testUpdateBookIfBookNotExist() throws Exception
    {
        doThrow(UserException.class).when(bookService).updateBook(anyLong(), any());
        BookDto bookDto = new BookDto("", "", "");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/books/1")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void testDeleteBook() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testDeleteBookIfBookNotExist() throws Exception
    {
        doThrow(UserException.class).when(bookService).deleteBook(anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

    public static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
