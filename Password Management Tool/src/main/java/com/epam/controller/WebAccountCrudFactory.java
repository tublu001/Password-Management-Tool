package com.epam.controller;

import com.epam.dao.MasterUserOperationsDao;
import com.epam.dao.MasterUsersOperationsDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.epam.controller.WebMasterController.userId;

@Controller
@RequestMapping("PMT")
public class WebAccountCrudFactory
{
    @Autowired
    private MasterUsersOperationsDao masterUsersOperationsDao;


    private static final Logger LOGGER = LogManager.getLogger(WebAccountCrudFactory.class);

    @PostMapping("UserCrudForm")
    public ModelAndView UserCrudMaster(String selection)
    {
        ModelAndView mv = new ModelAndView();

        String result = "success";
        LOGGER.info(selection);

        if (selection.equals("storeNewAccount"))
        {
            mv.setViewName("storeNewAccount");
            mv.addObject("user", masterUsersOperationsDao.getUser(userId).get());
            return mv;
        } else if (selection.equals("retrieveAllAccounts"))
        {
            mv.setViewName("retrieveAllAccounts");
            mv.addObject("user", masterUsersOperationsDao.getUser(userId).get());
            return mv;
        } else if (selection.equals("retrieveGroupWiseAccounts"))
        {
            mv.setViewName("retrieveGroupWiseAccounts");
            mv.addObject("user", masterUsersOperationsDao.getUser(userId).get());
            return mv;
        } else if (selection.equals("retrieveAccountPassword"))
        {
            mv.setViewName("retrieveAccountPassword");
            mv.addObject("user", masterUsersOperationsDao.getUser(userId).get());
            return mv;
        } else if (selection.equals("renameGroupName"))
        {
            mv.setViewName("renameGroupName");
            mv.addObject("user", masterUsersOperationsDao.getUser(userId).get());
            return mv;
        } else if (selection.equals("setPasswordPreference"))
        {
            mv.setViewName("setPasswordPreference");
            mv.addObject("user", masterUsersOperationsDao.getUser(userId).get());
            return mv;
        } else
        {

        }
        return mv;
    }


}
