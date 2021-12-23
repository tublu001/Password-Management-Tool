package com.epam.rest_controller;

import com.epam.controller.WebAccountCrudFactory;
import com.epam.dao.AccountsControllerDao;
import com.epam.dao.GroupOperationsDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.service.password_operations.PasswordOperations;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ContextConfiguration(classes = WebUserAccountsRestAPI.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = {"spring.cloud.discovery.enabled=false", "spring.cloud.config.enabled=false", "spring.profiles.active=test"})
@AutoConfigureMockMvc
class WebUserAccountsRestAPITest
{

//    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private User user;
    @MockBean
    private GroupOperationsDao groupOperations;
    @MockBean
    private AccountsControllerDao accountCredentialOperationsDao;
    @MockBean
    private MasterUserOperationsDao masterUserOperationsDao;
    @MockBean
    private PasswordOperations passwordOperations;
    @MockBean
    private MasterUsersOperationsDao masterUsersOperationsDao;
    @Autowired
    private WebApplicationContext webApplicationContext;

//    @InjectMocks
//    WebUserAccountsRestAPI underTest;

    @Test
    @WithMockUser(authorities = {"READ", "WRITE"})
    void storeAccountDetails()
    {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        UserAccountDTO userAccountDTO = new UserAccountDTO(user, "a", "xyz@com","qwerty", "G1");
        try
        {
            when(masterUsersOperationsDao.getUser(anyString())).thenReturn(Optional.ofNullable(user));
//            when(Optional.of(User.class).orElseThrow(()->any(Exception.class))).thenReturn(null);
            when(accountCredentialOperationsDao.storeAccount(any(UserAccountDTO.class))).thenReturn(true);
//          assertEquals(HttpStatus.OK.value() , underTest.storeAccountDetails(userAccountDTO).getStatusCodeValue());
//            responseEntity = underTest.storeAccountDetails(userAccountDTO);

//            mockMvc.perform(post("/api")
//                            .content(asJsonString(userAccountDTO))
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .with(csrf()))
//                    .andExpect(status().isOk())
//            .andDo(MockMvcResultHandlers.print());

            String uri = "http://localhost:9000/api";
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(userAccountDTO))).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println(content);
            Assertions.assertEquals(200, status);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
//        System.out.println(responseEntity.getStatusCodeValue());
//        assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.OK.value());
    }
    public static String asJsonString(Object obj) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper.writeValueAsString(obj);
    }
    public static <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void editAccountDetails()
    {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        UserAccountDTO userAccountDTO = new UserAccountDTO(null, "a", "xyz@com","qwerty", "G1");
        try
        {
            when(masterUsersOperationsDao.getUser(anyString())).thenReturn(Optional.ofNullable(user));
            when(accountCredentialOperationsDao.storeAccount(any(UserAccountDTO.class))).thenReturn(true);
        mockMvc.perform(put("/api")
                        .content(asJsonString(userAccountDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    } catch (Exception e)
    {
        e.printStackTrace();
    }
    }

    @Test
    void deleteAccountDetails()
    {

    }

    @Test
    void getAccounts()
    {
    }
}