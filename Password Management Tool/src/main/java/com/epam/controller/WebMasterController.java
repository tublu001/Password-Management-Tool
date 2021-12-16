package com.epam.controller;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.passwordOperations.UserLoginValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("PMT")
public class WebMasterController
{
    static Long userId;

    @Autowired
    private UserLoginValidation userLoginValidation;

    @Autowired
    private MasterUsersOperationsDao masterUsersOperationsDao;

    private static final Logger LOGGER = LogManager.getLogger(WebMasterController.class);

    @RequestMapping
    public String masterHomeMenu()
    {
        return "masterLogin";
    }

    @RequestMapping("masterSignUp")
    public String masterSignUp()
    {
        return "masterSignUp";
    }

    @RequestMapping("masterLogin")
    public String masterLogin()
    {
        return "masterLogin";
    }

    @PostMapping("loginMaster")
    public ModelAndView loginMaster(String username, String password)
    {
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            Optional<User> userOptional = userLoginValidation.validateMaster(username, password);
            userOptional.orElseThrow(() -> new UserException("User not present in database"));
            userId = userOptional.get().getUserId();
            modelAndView.addObject("user", masterUsersOperationsDao.getUser(userId).orElseThrow(() -> new UserException("User not found!!!")));
            modelAndView.setViewName("accountCrudMenu");
        } catch (UserException e)
        {
            e.printStackTrace();
            modelAndView.addObject("userException", e.getMessage());
            modelAndView.setViewName("masterLogin");
        }
        return modelAndView;
    }

    @PostMapping("registerUser")
    public ModelAndView signUpMaster(String userName, String password)
    {
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            if (masterUsersOperationsDao.addMasterUser(userName, password).isPresent())
            {
                modelAndView.addObject("successMessage", "Master User created... Kindly login here...");
                modelAndView.setViewName("masterLogin");
            }
        } catch (UserException e)
        {
            e.printStackTrace();
            modelAndView.addObject("userException", e.getMessage());
            modelAndView.setViewName("masterSignUp");
        }
        return modelAndView;
    }

    @RequestMapping("logout")
    public ModelAndView logout()
    {
        ModelAndView modelAndView = new ModelAndView();
        String userName = null;
        if (userId != null)
        {
            userName = masterUsersOperationsDao.getUser(userId).get().getUserName();
            modelAndView.addObject("successMessage", "Logged out of (" + userName + ")... ");
        }
        userId = null;
        modelAndView.setViewName("masterLogin");
        return modelAndView;
    }
}
