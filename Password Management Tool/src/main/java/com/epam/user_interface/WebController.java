package com.epam.user_interface;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserData;
import com.epam.passwordOperations.UserLoginValidation;
import com.epam.passwordOperations.UserLoginValidationImpl;
import com.epam.repository.MySQL_DB;
import com.epam.repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("PMT")
public class WebController
{
    static User globalUser;

    @Autowired
    private UserLoginValidation userLoginValidation;


    private static final Logger LOGGER = LogManager.getLogger(WebController.class);



    @RequestMapping
    public String masterHomeMenu()
    {
        return "masterHomeMenu";
    }

    @PostMapping("loginMaster")
    public String loginMaster(String username, String password)
    {
        MySQL_DB.initialize();
        LOGGER.debug("executing......");
        try
        {
            Optional<User> userOptional = userLoginValidation.validateMaster(username, password);
            if(userOptional.isPresent())
            {
                globalUser = userOptional.get();
                return "accountCrudMenu";
            }
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        return "error";
    }




}
