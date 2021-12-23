package com.epam.controller;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epam.controller.WebMasterController.userId;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//@WebMvcTest(controllers = WebAccountCrudFactory.class)
@ContextConfiguration(classes = WebAccountCrudFactory.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = {"spring.cloud.discovery.enabled=false", "spring.cloud.config.enabled=false", "spring.profiles.active=test"})
@AutoConfigureMockMvc
class WebAccountCrudFactoryTest
{

    @Autowired
    WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MasterUsersOperationsDao masterUsersOperationsDao;

    @MockBean
    User user;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    @WithMockUser(authorities = {"READ", "WRITE"})
    void whenValidInput_thenReturns200() throws Exception
    {
        List<String> requests = Arrays.asList(
                "storeNewAccount", "retrieveAllAccounts", "retrieveGroupWiseAccounts", "retrieveAccountPassword", "renameGroupName", "renameGroupName", "setPasswordPreference");

        requests.forEach(request ->
        {
            when(masterUsersOperationsDao.getUser(userId)).thenReturn(Optional.ofNullable(user));
            try
            {
                mockMvc.perform(post("/PMT/UserCrudForm?selection=" + request).with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(view().name(request))
                        .andDo(MockMvcResultHandlers.print());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    @Test
    void whenInvalidInput() throws Exception
    {
        when(masterUsersOperationsDao.getUser(userId)).thenReturn(Optional.ofNullable(user));
        mockMvc.perform(post("/PMT/UserCrudForm").with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void whenValidInput_thenReturns200_1() throws Exception
    {
        mockMvc.perform(post("/PMT/UserCrudForm?selection=none").with(csrf()))
                .andExpect(status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
    }
}