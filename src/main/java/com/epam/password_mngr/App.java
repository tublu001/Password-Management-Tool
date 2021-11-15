package com.epam.password_mngr;


import com.epam.UI.HomeMenu;

public class App 
{
    public static void main( String[] args )
    {        
    	HomeMenu.showHomeUI();		
    }
}










































//UserInterface obj = new displayData();
//obj.displayUsers();
//obj.displayAccounts();
//retrive objects from MasterUsers to display all passwords




//PasswordValidate pv = new UserVerify();
//MasterUsers user = pv.validateUser();
//if(user != null)
//{
//	AccountMenuCRUD.showCrudMenu(user);
//}
//

//Generator gen = new Generator();
//PreferredPassword prefPass = new PreferredPassword();
//prefPass.setPrefferdPassword();
//for(int x=0; x<10; x++)
//	System.out.println(gen.GeneratePassword(prefPass));



//Generator gen = new Generator();
//MasterUsers user = new MasterUsers();
//PreferredPassword prefPass = user.getPrefPass();
//
////String pss = gen.GeneratePassword(prefPass);
////System.out.println(prefPass.getLength());
////System.out.println(pss);
//System.out.println(prefPass.generatePassword(prefPass));