package com.epam.controller;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.GroupOperationsDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.dto.PreferredPasswordDTO;
import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.epam.controller.WebMasterController.globalUser;

@Controller
@RequestMapping("PMT")
public class WebAccountCrudRequestHandler
{
    private static final Logger LOGGER = LogManager.getLogger(WebAccountCrudRequestHandler.class);
    @Autowired
    private AccountsControllerDao accountsControllerDao;
    @Autowired
    private GroupOperationsDao groupOperations;
    @Autowired
    private AccountsControllerDao accountCredentialOperationsDao;
    @Autowired
    private MasterUserOperationsDao masterUserOperationsDao;

    @PostMapping("storeAccount")
    public ModelAndView storeAccountDetails(String appName, String url, String password, String accountGroup)
    {
        ModelAndView modelAndView = new ModelAndView();
        UserAccountDTO userDetail = new UserAccountDTO(globalUser, appName, url, password, accountGroup);
        try
        {
            modelAndView.addObject("user", globalUser);
            modelAndView.setViewName("error");
            if (accountsControllerDao.store(userDetail))
            {
                modelAndView.addObject("successMessage", "Account Added successfully...");
                modelAndView.setViewName("storeNewAccount");
            }
        } catch (UserException e)
        {
            e.printStackTrace();
            modelAndView.addObject("userException", e.getMessage());
            modelAndView.setViewName("storeNewAccount");
        }
        return modelAndView;
    }

    @PostMapping("retrieveAccountPassword")
    public ModelAndView retrievePassword(String appName)
    {
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            modelAndView.addObject("user", globalUser);
            modelAndView.setViewName("error");
            String retrievedPassword = accountsControllerDao.retrievePassword(globalUser, appName);
            modelAndView.addObject("successMessage", retrievedPassword);
            modelAndView.setViewName("retrieveAccountPassword");
        } catch (UserException e)
        {
            e.printStackTrace();
            modelAndView.addObject("userException", e.getMessage());
            modelAndView.setViewName("retrieveAccountPassword");
        }
        return modelAndView;
    }

    @PostMapping("renameGroupName")
    public ModelAndView renameGroupName(String oldGroupName, String newGroupName)
    {
        ModelAndView modelAndView = new ModelAndView();
        boolean isRenamed = false;
        try
        {
            modelAndView.addObject("user", globalUser);
            isRenamed = groupOperations.updateGroupName(globalUser, oldGroupName, newGroupName);
        } catch (UserException e)
        {
            e.printStackTrace();
            modelAndView.addObject("userException", e.getMessage());
            modelAndView.setViewName("renameGroupName");
        }
        if (isRenamed)
        {
            modelAndView.addObject("successMessage", "Group renamed from " + oldGroupName + " ----> " + newGroupName);
            modelAndView.setViewName("renameGroupName");
        }
        return modelAndView;
    }

    @GetMapping("deleteAccountCredential")
    public ModelAndView deleteAccountCredential(String appName)
    {
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            accountCredentialOperationsDao.remove(globalUser, appName, globalUser.getPassword());
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        modelAndView.setViewName("retrieveAllAccounts");
        modelAndView.addObject("successMessage", "App deleted successfully...");
        modelAndView.addObject("user", globalUser);
        return modelAndView;
    }

    @PostMapping("passwordPreference")
    public ModelAndView setPreferredPassword(PreferredPasswordDTO preferredPasswordDTO) throws UserException
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", globalUser);
        modelAndView.setViewName("error");
        boolean success = masterUserOperationsDao.setPreferredPassword(globalUser, preferredPasswordDTO.getPreferredPasswordObject());
        if (success)
        {
            modelAndView.addObject("successMessage", "You have successfully saved your Password Preference...");
            modelAndView.setViewName("accountCrudMenu");
        }
        return modelAndView;
    }


    @GetMapping("crudMenu")
    public ModelAndView accountCrudMenu()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", globalUser);
        modelAndView.setViewName("accountCrudMenu");
        return modelAndView;
    }
}
