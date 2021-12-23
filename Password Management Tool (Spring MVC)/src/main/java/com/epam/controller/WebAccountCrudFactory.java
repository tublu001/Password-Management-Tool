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
import static com.epam.utility.constants.MASTER_NOT_FOUND;

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
        ModelAndView mv = new ModelAndView();

        String result = "success";
        LOGGER.info(selection);

        if (selection.equals("storeNewAccount"))
        {
            mv.setViewName("storeNewAccount");
            mv.addObject("user", getMasterUserBySessionId());
            return mv;
        } else if (selection.equals("retrieveAllAccounts"))
        {
            mv.setViewName("retrieveAllAccounts");
            mv.addObject("user", getMasterUserBySessionId());
            return mv;
        } else if (selection.equals("retrieveGroupWiseAccounts"))
        {
            mv.setViewName("retrieveGroupWiseAccounts");
            mv.addObject("user", getMasterUserBySessionId());
            return mv;
        } else if (selection.equals("retrieveAccountPassword"))
        {
            mv.setViewName("retrieveAccountPassword");
            mv.addObject("user", getMasterUserBySessionId());
            return mv;
        } else if (selection.equals("renameGroupName"))
        {
            mv.setViewName("renameGroupName");
            mv.addObject("user", getMasterUserBySessionId());
            return mv;
        } else if (selection.equals("setPasswordPreference"))
        {
            mv.setViewName("setPasswordPreference");
            mv.addObject("user", getMasterUserBySessionId());
            return mv;
        } else
        {

        }
        return mv;
    }

    private User getMasterUserBySessionId() throws UserException
    {
        return masterUsersOperationsDao.getUser(userId).orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
    }


}
