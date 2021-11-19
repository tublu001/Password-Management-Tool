package com.epam.UI;

import java.util.Scanner;

import com.epam.model.MasterUsers;
import com.epam.service.AccountCrudFactory;
import com.epam.service.UserAccountCrudOperation;

public class AccountMenuCRUD {

	public AccountMenuCRUD() 
	{}
	
	@SuppressWarnings(value = {"all"})
	public static void showCrudMenu(MasterUsers user)
	{
		boolean repeatLoop = true;
		final Scanner input = new Scanner(System.in);
		
		while(repeatLoop)
		{
			System.out.println("\n\nChoose any of the Operation");
			System.out.println("1. Store a Account Credential");
			System.out.println("2. Retrive all Account details");
			System.out.println("3. Retrive Group wise Accounts details");
			System.out.println("4. Retrive any Account details");
			System.out.println("5. Rename any Group name");
			System.out.println("6. Delete any Account Credential");
			System.out.println("7. Set your Password preference");
			System.out.print("0. Sign Out\n\n\nChoose Any: ");
			
			char selection = input.next().charAt(0);
			
			UserAccountCrudOperation operations = new AccountCrudFactory().getOperation(selection);
			if(operations!=null)
				operations.execute(user);
			
			if(selection == '0')
				repeatLoop = false;
		}
		
	}

}
