package com.epam.user_interface;

import com.epam.exceptions.UserException;
import com.epam.model.UserData;
import com.epam.service.AcquireAccountCredentials;
import com.epam.service.DeleteAccountCredential;
import com.epam.service.RenameGroupName;
import com.epam.service.RetrieveAccountPassword;
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
    @Autowired
    AcquireAccountCredentials acquireAccountCredentials;
    @Autowired
    RetrieveAccountPassword retrieveAccountPassword;
    @Autowired
    RenameGroupName renameGroupName;
    @Autowired
    DeleteAccountCredential deleteAccountCredential;

    ModelAndView mv = new ModelAndView();

    @PostMapping("storeAccount")
    public String storeAccountDetails(String appName, String url, String password, String accountGroup)
    {
        UserData userDetail = new UserData(globalUser, appName, url, password, accountGroup);
        try
        {
            if(acquireAccountCredentials.addAccount(userDetail))
                return "accountCrudMenu";
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        return "error";
    }

    @PostMapping("retrieveAccountPassword")
    public String retrievePassword(String appName)
    {
        try
        {
            return retrieveAccountPassword.retrievePassword(globalUser, appName);
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        return "error";
    }

    @PostMapping("renameGroupName")
    public String renameGroupName(String oldGroupName, String newGroupName)
    {
        boolean isRenamed=false;
        try
        {
            isRenamed = renameGroupName.renameGroup(globalUser, oldGroupName, newGroupName);
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        if(isRenamed)
            return "success";
        else
            return "error";
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
        mv.addObject("accounts", globalUser.getAccounts());
        return mv;
    }


    @GetMapping("crudMenu")
    public String accountCrudMenu()
    {
        return "accountCrudMenu";
    }
}
