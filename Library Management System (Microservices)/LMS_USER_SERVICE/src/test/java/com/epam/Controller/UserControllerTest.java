package com.epam.Controller;

import com.epam.DTO.UserDto;
import com.epam.Service.UserServices;
import com.epam.exceptions.UserException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserController.class)
@AutoConfigureMockMvc
class UserControllerTest
{
    @MockBean
    UserServices UserService;
    @Autowired
    MockMvc mockMvc;


    @Test
    void testUserById() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/Users/ram").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAllUsers() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/Users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUserByIdIfUserNotExist() throws Exception
    {
        doThrow(UserException.class).when(UserService).getUserByUserName(anyString());
        mockMvc.perform(MockMvcRequestBuilders.get("/Users/ram").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveUser() throws Exception
    {
        UserDto UserDto = new UserDto("ram", "Ayodhya", "RamTiwari");
        mockMvc.perform(MockMvcRequestBuilders.post("/Users/").content(asJsonString(UserDto))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

    }


    @Test
    void testUpdateUser() throws Exception
    {
        UserDto UserDto = new UserDto("ram", "Ayodhya", "RamTiwari");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/Users/1")
                        .content(asJsonString(UserDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUserIfUserNotExist() throws Exception
    {
        doThrow(UserException.class).when(UserService).updateUserByUserName(anyString(), any());
        UserDto UserDto = new UserDto("", "", "");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/Users/1")
                        .content(asJsonString(UserDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUser() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUserIfUserNotExist() throws Exception
    {
        doThrow(UserException.class).when(UserService).deleteUserByUserName(anyString());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
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