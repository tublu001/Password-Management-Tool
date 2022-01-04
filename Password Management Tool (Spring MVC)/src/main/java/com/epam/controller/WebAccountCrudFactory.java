package com.epam.controller;

import com.epam.dao.MasterUsersOperationsDao;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.epam.controller.WebMasterController.userId;
import static com.epam.utility.Constants.MASTER_NOT_FOUND;

@Controller
@RequestMapping("PMT")
public class WebAccountCrudFactory
{
    @Autowired
    private MasterUsersOperationsDao masterUsersOperationsDao;


    private static final Logger LOGGER = LogManager.getLogger(WebAccountCrudFactory.class);

    @PostMapping("UserCrudForm")
    public ModelAndView UserCrudMaster(String selection) throws UserException
    {
        ModelAndView modelAndView = new ModelAndView();

        String result = "success";
        LOGGER.info(selection);
        try
        {
            if(selection == null)
            {
                throw new UserException("Select any option above");
            }
            if (selection.equals("storeNewAccount"))
            {
                modelAndView.setViewName("storeNewAccount");
                modelAndView.addObject("user", getMasterUserBySessionId());
                return modelAndView;
            } else if (selection.equals("retrieveAllAccounts"))
            {
                modelAndView.setViewName("retrieveAllAccounts");
                modelAndView.addObject("user", getMasterUserBySessionId());
                return modelAndView;
            } else if (selection.equals("retrieveGroupWiseAccounts"))
            {
                modelAndView.setViewName("retrieveGroupWiseAccounts");
                modelAndView.addObject("user", getMasterUserBySessionId());
                return modelAndView;
            } else if (selection.equals("retrieveAccountPassword"))
            {
                modelAndView.setViewName("retrieveAccountPassword");
                modelAndView.addObject("user", getMasterUserBySessionId());
                return modelAndView;
            } else if (selection.equals("renameGroupName"))
            {
                modelAndView.setViewName("renameGroupName");
                modelAndView.addObject("user", getMasterUserBySessionId());
                return modelAndView;
            } else if (selection.equals("setPasswordPreference"))
            {
                modelAndView.setViewName("setPasswordPreference");
                modelAndView.addObject("user", getMasterUserBySessionId());
                return modelAndView;
            } else
            {

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            modelAndView.addObject("userException", e.getMessage());
            modelAndView.setViewName("accountCrudMenu");
        }
        return modelAndView;
    }

    private User getMasterUserBySessionId() throws UserException
    {
        return masterUsersOperationsDao.getUser(userId).orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
    }


}
