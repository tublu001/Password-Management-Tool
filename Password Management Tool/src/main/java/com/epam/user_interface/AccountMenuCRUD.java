package com.epam.user_interface;

import com.epam.model.User;
import com.epam.service.AccountCrudFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class AccountMenuCRUD
{

    public AccountMenuCRUD()
    {
    }

    private static final Logger LOGGER = LogManager.getLogger(AccountMenuCRUD.class);

    @Autowired
    private AccountCrudFactory accountCrudFactory;

    @SuppressWarnings(value = {"all"})
    public void showCrudMenu(User user)
    {
        boolean repeatLoop = true;
        final Scanner input = new Scanner(System.in);

        while (repeatLoop)
        {
            LOGGER.info("\n\nChoose any of the Operation");
            LOGGER.info("1. Store a Account Credential");
            LOGGER.info("2. Retrive all Account details");
            LOGGER.info("3. Retrive Group wise Accounts details");
            LOGGER.info("4. Retrive any Account details");
            LOGGER.info("5. Rename any Group name");
            LOGGER.info("6. Delete any Account Credential");
            LOGGER.info("7. Set your Password preference");
            LOGGER.info("0. Sign Out\n\n\nChoose Any: ");

            String selection = input.next();


            try
            {
                UserAccountCrudOperation operations = accountCrudFactory.getOperation(selection);
                if (operations != null)
                {
                    operations.execute(user);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            if (selection.equals("0"))
            {
//                MySQL_DB.close();
                repeatLoop = false;
            }
        }

    }

}
