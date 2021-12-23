package com.epam.rest_controller;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.GroupOperationsDao;
import com.epam.dao.MasterUserOperationsDao;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.service.password_operations.PasswordOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class WebUserAccountsRestAPI
{
    private static final Logger LOGGER = LogManager.getLogger(WebUserAccountsRestAPI.class);
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

    @PostMapping
    public ResponseEntity<String> storeAccountDetails(@RequestBody UserAccountDTO userAccountDTO) throws UserException
    {
        String response = "Not Stored";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        System.out.println(userAccountDTO);
        User user = masterUsersOperationsDao.getUser(userAccountDTO.getMasterName()).orElseThrow(()->new UserException("Master not found!!!"));
        userAccountDTO.setUser(user);
        if(accountCredentialOperationsDao.storeAccount(userAccountDTO))
        {
            response = "Account inserted successfully.";
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<String>(response,statusCode);
    }

    @PutMapping
    public ResponseEntity<String> editAccountDetails(@RequestBody UserAccountDTO userAccountDTO) throws UserException
    {
        String response = "Not Updated";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        System.out.println(userAccountDTO);
        User user = masterUsersOperationsDao.getUser(userAccountDTO.getMasterName()).orElseThrow(()->new UserException("Master not found!!!"));
        userAccountDTO.setUser(user);
        if(accountCredentialOperationsDao.editAccount(userAccountDTO))
        {
            response = "Account updated successfully.";
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<String>(response,statusCode);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAccountDetails(@RequestBody UserAccountDTO userAccountDTO) throws UserException
    {
        String response = "Not Deleted";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        System.out.println(userAccountDTO);
        User user = masterUsersOperationsDao.getUser(userAccountDTO.getMasterName()).orElseThrow(()->new UserException("Master not found!!!"));
        userAccountDTO.setUser(user);
        if(accountCredentialOperationsDao.removeAccount(Optional.ofNullable(user), userAccountDTO.getAppName(), userAccountDTO.getMasterPassword()))
        {
            response = "Account deleted successfully.";
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<String>(response,statusCode);
    }

    @GetMapping
    public ResponseEntity<List<UserAccountDTO>> getAccounts(String masterName) throws UserException
    {
        User user = masterUsersOperationsDao.getUser(masterName).orElseThrow(()->new UserException("Master not found!!!"));
        List<UserAccountDTO> accountDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(UserAccount tempAccounts : user.getAccounts())
        {
            UserAccountDTO tempAccountDTO = new UserAccountDTO();
            modelMapper.map(tempAccounts, tempAccountDTO);
//            tempAccountDTO.setUser(null);
            accountDTOS.add(tempAccountDTO);
        }
        return new ResponseEntity<>(accountDTOS,HttpStatus.OK);
    }



//
//    @PostMapping("retrieveAccountPassword")
//    public ModelAndView retrievePassword(String appName)
//    {
//        ModelAndView modelAndView = new ModelAndView();
//        try
//        {
//            modelAndView.addObject("user", masterUsersOperationsDao.getUser(userId).get());
//            modelAndView.setViewName("error");
//            String retrievedPassword = accountsControllerDao.retrievePassword(masterUsersOperationsDao.getUser(userId), appName);
//            modelAndView.addObject("successMessage", "password is: " + retrievedPassword);
//            modelAndView.setViewName("retrieveAccountPassword");
//        } catch (UserException e)
//        {
//            e.printStackTrace();
//            modelAndView.addObject("userException", e.getMessage());
//            modelAndView.setViewName("retrieveAccountPassword");
//        }
//        return modelAndView;
//    }
//
//    @PostMapping("renameGroupName")
//    public ModelAndView renameGroupName(String oldGroupName, String newGroupName)
//    {
//        ModelAndView modelAndView = new ModelAndView();
//        boolean isRenamed = false;
//        try
//        {
//            modelAndView.addObject("user", masterUsersOperationsDao.getUser(userId).get());
//            isRenamed = groupOperations.updateGroupName(masterUsersOperationsDao.getUser(userId).get(), oldGroupName, newGroupName);
//        } catch (UserException e)
//        {
//            e.printStackTrace();
//            modelAndView.addObject("userException", e.getMessage());
//            modelAndView.setViewName("renameGroupName");
//        }
//        if (isRenamed)
//        {
//            modelAndView.addObject("successMessage", "Group renamed from " + oldGroupName + " ----> " + newGroupName);
//            modelAndView.setViewName("renameGroupName");
//        }
//        return modelAndView;
//    }
//
//    @GetMapping("deleteAccountCredential")
//    public ModelAndView deleteAccountCredential(String appName)
//    {
//        ModelAndView modelAndView = new ModelAndView();
//        try
//        {
//            User user = masterUsersOperationsDao.getUser(userId).get();
//            accountCredentialOperationsDao.removeAccount(Optional.ofNullable(user), appName, passwordOperations.decryptPassword(user.getPassword()));
//        } catch (UserException e)
//        {
//            e.printStackTrace();
//        }
//        modelAndView.setViewName("retrieveAllAccounts");
//        modelAndView.addObject("successMessage", "App deleted successfully...");
//        modelAndView.addObject("user", masterUsersOperationsDao.getUser(userId).get());
//        return modelAndView;
//    }
//
//    @PostMapping("passwordPreference")
//    public ModelAndView setPreferredPassword(PreferredPasswordDTO preferredPasswordDTO) throws UserException
//    {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", masterUsersOperationsDao.getUser(userId).get());
//        modelAndView.setViewName("error");
//        boolean success = masterUserOperationsDao.setPreferredPassword(masterUsersOperationsDao.getUser(userId).get(), preferredPasswordDTO.getPreferredPasswordObject());
//        if (success)
//        {
//            modelAndView.addObject("successMessage", "You have successfully saved your Password Preference...");
//            modelAndView.setViewName("accountCrudMenu");
//        }
//        return modelAndView;
//    }
//
//
//    @GetMapping("crudMenu")
//    public ModelAndView accountCrudMenu()
//    {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", masterUsersOperationsDao.getUser(userId).get());
//        modelAndView.setViewName("accountCrudMenu");
//        return modelAndView;
//    }
//
//
//    @GetMapping("editAccountCredential")
//    public ModelAndView editAccountCredential(String appName)
//    {
//        ModelAndView modelAndView = new ModelAndView();
//        try
//        {
//            User user = masterUsersOperationsDao.getUser(userId).get();
//            UserAccount account = accountCredentialOperationsDao.getAccountByAppName(Optional.ofNullable(user), appName).orElseThrow(() -> new UserException("Can't find the application!!!"));
//            modelAndView.addObject("user", user);
//            modelAndView.addObject("account", account);
//            modelAndView.setViewName("editAccountDetails");
//        } catch (UserException e)
//        {
//            e.printStackTrace();
//        }
//        return modelAndView;
//    }
//
//    @PostMapping("editAccount")
//    public ModelAndView editAccountCredential(UserAccountDTO userAccountDTO)
//    {
//        ModelAndView modelAndView = new ModelAndView();
//        try
//        {
//            userAccountDTO.setUser(masterUsersOperationsDao.getUser(userId).orElseThrow(() -> new UserException("Can't find the user!!!")));
//            modelAndView.addObject("user", masterUsersOperationsDao.getUser(userId).get());
//            modelAndView.setViewName("error");
//            if (accountsControllerDao.editAccount(userAccountDTO))
//            {
//                modelAndView.addObject("successMessage", "Account Updated successfully...");
//                modelAndView.setViewName("retrieveAllAccounts");
//            }
//        } catch (UserException e)
//        {
//            e.printStackTrace();
//            modelAndView.addObject("userException", e.getMessage());
//            modelAndView.setViewName("retrieveAllAccounts");
//        }
//        return modelAndView;
//    }

}
