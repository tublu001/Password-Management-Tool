package com.epam.UI;

import java.util.Scanner;

import com.epam.MasterGroups.MasterCrudMenu;
import com.epam.MasterGroups.MasterOperations;
import com.epam.MasterGroups.MasterUsers;
import com.epam.userAccounts.AccountMenuCRUD;

public class HomeMenu 
{

	public HomeMenu() 
	{}
	
	@SuppressWarnings(value = {"all"})
	public static void showHomeUI()
	{
		int flag = 0;
		final Scanner input = new Scanner(System.in);
		
		while(flag==0)
		{
			System.out.println("\n\n*************** WELCOME TO PASSWORD MANAGEMENT TOOL ***************\n\n");
			System.out.println("1. Log In to your Master Account");
			System.out.println("2. Sign Up for a Master Account");
			System.out.println("3. Retrive all Master user names");
//			System.out.println("4. Delete any Account Credential");
//			System.out.println("5. Set your Password preference");
			System.out.print("0. Exit..\n\n\nChoose Any: ");
			
			char ch = input.next().charAt(0);
			
			MasterCrudMenu op = new MasterOperations();
			switch(ch)
			{
				case '1':
					MasterUsers user = op.loginMaster();
					if(user!=null)
						AccountMenuCRUD.showCrudMenu(user);
					break;
				case '2':
					op.createMaster();
					break;
				case '3':
					op.showAllMasters();
					break;
	//			case 4:
	//				op.deleteAccountCredential(user);
	//				break;
	//			case 5:
	//				op.setPasswordPreference(user);
	//				break;
				case '4':
					flag = 1;
					System.out.println("Thank you... Exiting...");
					break;
				default:
					System.out.println("Invalid Input! Try again...");
					break;
			}
		}
	}

}
