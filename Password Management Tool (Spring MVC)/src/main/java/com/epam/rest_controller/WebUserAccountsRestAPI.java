package com.epam.rest_controller;

import com.epam.dao.AccountsControllerDao;
import com.epam.dao.MasterUsersOperationsDao;
import com.epam.dto.UserAccountDTO;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.model.UserAccount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.utility.constants.MASTER_NOT_FOUND;


@RestController
@RequestMapping("/api")
public class WebUserAccountsRestAPI
{
    @Autowired
    private AccountsControllerDao accountCredentialOperationsDao;
    @Autowired
    private MasterUsersOperationsDao masterUsersOperationsDao;

    @PostMapping
    public ResponseEntity<String> storeAccountDetails(@RequestBody UserAccountDTO userAccountDTO) throws UserException
    {
        String response = "Not Stored";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        System.out.println(userAccountDTO);
        User user = getMasterUser(userAccountDTO.getMasterName());
        userAccountDTO.setUser(user);
        if (accountCredentialOperationsDao.storeAccount(userAccountDTO))
        {
            response = "Account inserted successfully.";
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping
    public ResponseEntity<String> editAccountDetails(@RequestBody UserAccountDTO userAccountDTO) throws UserException
    {
        String response = "Not Updated";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        System.out.println(userAccountDTO);
        User user = getMasterUser(userAccountDTO.getMasterName());
        userAccountDTO.setUser(user);
        if (accountCredentialOperationsDao.editAccount(userAccountDTO))
        {
            response = "Account updated successfully.";
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAccountDetails(@RequestBody UserAccountDTO userAccountDTO) throws UserException
    {
        String response = "Not Deleted";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        System.out.println(userAccountDTO);
        User user = getMasterUser(userAccountDTO.getMasterName());
        userAccountDTO.setUser(user);
        if (accountCredentialOperationsDao.removeAccount(Optional.ofNullable(user), userAccountDTO.getAppName(), userAccountDTO.getMasterPassword()))
        {
            response = "Account deleted successfully.";
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("accounts")
    public ResponseEntity<List<UserAccountDTO>> getAccounts(String masterName) throws UserException
    {
        User user = getMasterUser(masterName);
        List<UserAccountDTO> accountDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (UserAccount tempAccounts : user.getAccounts())
        {
            UserAccountDTO tempAccountDTO = new UserAccountDTO();
            modelMapper.map(tempAccounts, tempAccountDTO);
            accountDTOS.add(tempAccountDTO);
        }
        return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<User> getUser(String masterName) throws UserException
    {
        User user = getMasterUser(masterName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    private User getMasterUser(String masterName) throws UserException
    {
        return masterUsersOperationsDao.getUser(masterName).orElseThrow(() -> new UserException(MASTER_NOT_FOUND));
    }

}
