package com.epam.controller;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.service.UserLoginValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.epam.utility.Constants.MASTER_NOT_FOUND;

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
            userOptional.orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
            userId = userOptional.get().getUserId();
            modelAndView.addObject("user", getMasterUserBySessionId());
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
    public ModelAndView logout() throws UserException
    {
        ModelAndView modelAndView = new ModelAndView();
        String userName = null;
        if (userId != null)
        {
            userName = getMasterUserBySessionId().getUserName();
            modelAndView.addObject("successMessage", "Logged out of (" + userName + ")... ");
        }
        userId = null;
        modelAndView.setViewName("masterLogin");
        return modelAndView;
    }

    private User getMasterUserBySessionId() throws UserException
    {
        return masterUsersOperationsDao.getUser(userId).orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
    }
}
