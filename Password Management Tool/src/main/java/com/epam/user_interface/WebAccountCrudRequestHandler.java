package com.epam.user_interface;

import com.epam.exceptions.UserException;
import com.epam.model.PreferredPasswordObject;
import com.epam.model.UserData;
import com.epam.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.epam.user_interface.WebController.globalUser;

@Controller
@RequestMapping("PMT")
public class WebAccountCrudRequestHandler
{
    private static final Logger LOGGER = LogManager.getLogger(WebAccountCrudRequestHandler.class);
    @Autowired
    private AcquireAccountCredentials acquireAccountCredentials;
    @Autowired
    private RetrieveAccountPassword retrieveAccountPassword;
    @Autowired
    private RenameGroupName renameGroupName;
    @Autowired
    private DeleteAccountCredential deleteAccountCredential;
    @Autowired
    private SetPasswordPreference setPasswordPreference;

    ModelAndView mv = new ModelAndView();

    @PostMapping("storeAccount")
    public ModelAndView storeAccountDetails(String appName, String url, String password, String accountGroup)
    {
        UserData userDetail = new UserData(globalUser, appName, url, password, accountGroup);
        try
        {
            mv.addObject("user", globalUser);
            mv.setViewName("error");
            if(acquireAccountCredentials.addAccount(userDetail))
            {
                mv.setViewName("accountCrudMenu");
            }
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        return mv;
    }

    @PostMapping("retrieveAccountPassword")
    public ModelAndView retrievePassword(String appName)
    {
        try
        {
            mv.addObject("user", globalUser);
            mv.setViewName("error");
            retrieveAccountPassword.retrievePassword(globalUser, appName);
            mv.setViewName("accountCrudMenu");
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        return mv;
    }

    @PostMapping("renameGroupName")
    public ModelAndView renameGroupName(String oldGroupName, String newGroupName)
    {
        boolean isRenamed=false;
        try
        {
            mv.addObject("user", globalUser);
            mv.setViewName("error");
            isRenamed = renameGroupName.renameGroup(globalUser, oldGroupName, newGroupName);
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        if(isRenamed)
            mv.setViewName("accountCrudMenu");
        return mv;
    }

    @GetMapping("deleteAccountCredential")
    public ModelAndView deleteAccountCredential(String appName)
    {
        try
        {
            deleteAccountCredential.deleteAccount(globalUser, appName, globalUser.getPassword());
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        mv.setViewName("retrieveAllAccounts");
        mv.addObject("user", globalUser);
        return mv;
    }

    @PostMapping("passwordPreference")
    public ModelAndView setPreferredPassword(PreferredPasswordObject preferredPasswordObject)
    {
        mv.addObject("user", globalUser);
        mv.setViewName("error");
        boolean success = setPasswordPreference.setPreferredPassword(globalUser, preferredPasswordObject.getPreferredPasswordObject());
        if(success)
        {
            mv.setViewName("accountCrudMenu");
        }
        return mv;
    }


    @GetMapping("crudMenu")
    public String accountCrudMenu()
    {
        return "accountCrudMenu";
    }
}
