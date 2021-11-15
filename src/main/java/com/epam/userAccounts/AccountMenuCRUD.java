package com.epam.userAccounts;

import java.util.Scanner;

import com.epam.MasterGroups.MasterUsers;

public class AccountMenuCRUD {

	public AccountMenuCRUD() 
	{}
	
	@SuppressWarnings(value = {"all"})
	public static void showCrudMenu(MasterUsers user)
	{
		int flag = 0;
		final Scanner input = new Scanner(System.in);
		
		while(flag==0)
		{
			System.out.println("\n\nChoose any of the Operation");
			System.out.println("1. Store a Account Credential");
			System.out.println("2. Retrive all Account Credential");
			System.out.println("3. Retrive any Account Credential");
			System.out.println("4. Delete any Account Credential");
			System.out.println("5. Set your Password preference");
			System.out.print("0. Sign Out\n\n\nChoose Any: ");
			
			char ch = input.next().charAt(0);
			CrudMenuOperations op = new UserAccountOperations();
			switch(ch)
			{
				case '1':
					op.acquireAccountCredentials(user);
					break;
				case '2':
					op.retriveAllAccounts(user);
					break;
				case '3':
					op.retriveAccountCredential(user);
					break;
				case '4':
					op.deleteAccountCredential(user);
					break;
				case '5':
					op.setPasswordPreference(user);
					break;
				case '0':
					flag = 1;
					System.out.println("Thank you... Signing Out...");
					break;
				default:
					System.out.println("Invalid Input! Try again...");
					break;
			}
		}
		
	}

}
