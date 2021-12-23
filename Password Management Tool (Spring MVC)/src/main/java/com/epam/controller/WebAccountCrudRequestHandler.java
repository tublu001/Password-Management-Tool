package com.epam.controller;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.GroupOperationsDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.dto.PreferredPasswordDTO;
import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.service.password_operations.PasswordOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.epam.controller.WebMasterController.userId;
import static com.epam.utility.constants.MASTER_NOT_FOUND;

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
    @Autowired
    private PasswordOperations passwordOperations;
    @Autowired
    private MasterUsersOperationsDao masterUsersOperationsDao;

    @PostMapping("storeAccount")
    public ModelAndView storeAccountDetails(UserAccountDTO userAccountDTO) throws UserException
    {
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            userAccountDTO.setUser(getMasterUserBySessionId());
            modelAndView.addObject("user", getMasterUserBySessionId());
            modelAndView.setViewName("error");
            if (accountsControllerDao.storeAccount(userAccountDTO))
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
            modelAndView.addObject("user", getMasterUserBySessionId());
            modelAndView.setViewName("error");
            String retrievedPassword = accountsControllerDao.retrievePassword(masterUsersOperationsDao.getUser(userId), appName);
            modelAndView.addObject("successMessage", "password is: " + retrievedPassword);
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
            modelAndView.addObject("user", getMasterUserBySessionId());
            isRenamed = groupOperations.updateGroupName(getMasterUserBySessionId(), oldGroupName, newGroupName);
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
    public ModelAndView deleteAccountCredential(String appName) throws UserException
    {
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            User user = getMasterUserBySessionId();
            accountCredentialOperationsDao.removeAccount(Optional.ofNullable(user), appName, passwordOperations.decryptPassword(user.getPassword()));
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        modelAndView.setViewName("retrieveAllAccounts");
        modelAndView.addObject("successMessage", "App deleted successfully...");
        modelAndView.addObject("user", getMasterUserBySessionId());
        return modelAndView;
    }

    @PostMapping("passwordPreference")
    public ModelAndView setPreferredPassword(PreferredPasswordDTO preferredPasswordDTO) throws UserException
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", getMasterUserBySessionId());
        modelAndView.setViewName("error");
        boolean success = masterUserOperationsDao.setPreferredPassword(getMasterUserBySessionId(), preferredPasswordDTO.getPreferredPasswordObject());
        if (success)
        {
            modelAndView.addObject("successMessage", "You have successfully saved your Password Preference...");
            modelAndView.setViewName("accountCrudMenu");
        }
        return modelAndView;
    }


    @GetMapping("crudMenu")
    public ModelAndView accountCrudMenu() throws UserException
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", getMasterUserBySessionId());
        modelAndView.setViewName("accountCrudMenu");
        return modelAndView;
    }


    @GetMapping("editAccountCredential")
    public ModelAndView editAccountCredential(String appName)
    {
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            User user = getMasterUserBySessionId();
            UserAccount account = accountCredentialOperationsDao.getAccountByAppName(Optional.ofNullable(user), appName).orElseThrow(() -> new UserException("Can't find the application!!!"));
            modelAndView.addObject("user", user);
            modelAndView.addObject("account", account);
            modelAndView.setViewName("editAccountDetails");
        } catch (UserException e)
        {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping("editAccount")
    public ModelAndView editAccountCredential(UserAccountDTO userAccountDTO)
    {
        ModelAndView modelAndView = new ModelAndView();
        try
        {
            userAccountDTO.setUser(getMasterUserBySessionId());
            modelAndView.addObject("user", getMasterUserBySessionId());
            modelAndView.setViewName("error");
            if (accountsControllerDao.editAccount(userAccountDTO))
            {
                modelAndView.addObject("successMessage", "Account Updated successfully...");
                modelAndView.setViewName("retrieveAllAccounts");
            }
        } catch (UserException e)
        {
            e.printStackTrace();
            modelAndView.addObject("userException", e.getMessage());
            modelAndView.setViewName("retrieveAllAccounts");
        }
        return modelAndView;
    }

    private User getMasterUserBySessionId() throws UserException
    {
        return masterUsersOperationsDao.getUser(userId).orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
    }

    private User getMasterUser(String masterName) throws UserException
    {
        return masterUsersOperationsDao.getUser(masterName).orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
    }

}
