/**
 * 
 */
package com.epam.MasterGroups;

import java.util.ArrayList;

import com.epam.passwordOperations.PasswordOperations;
import com.epam.passwordOperations.PreferredPassword;
import com.epam.passwordOperations.PwdOperate;
import com.epam.userAccounts.UserAccount;

/**
 * @author Manash_Rauta
 *
 */
public class MasterUsers implements MasterUserCredentials
{
	private static ArrayList<MasterUsers> masterUsers = new ArrayList<MasterUsers>();
	
	private String userName;
	private String password;
	private ArrayList<UserAccount> accounts;
	private PreferredPassword prefPass;
	//private UserAccountCredential accounts;
	
	static
	{
//		add("Manash", "qwerty");
		PasswordOperations operate = new PwdOperate();
		masterUsers.add(new MasterUsers().add("Manash", operate.encryptPassword("qwerty")));
		masterUsers.add(new MasterUsers().add("Suresh", operate.encryptPassword("bfb")));
		masterUsers.add(new MasterUsers().add("Roshan", operate.encryptPassword("dewdw")));
		masterUsers.add(new MasterUsers().add("Lokesh", operate.encryptPassword("odhc")));
	}
	
	public static ArrayList<MasterUsers> getMasterUsers() {
		return masterUsers;
	}

	public ArrayList<UserAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<UserAccount> accounts) {
		this.accounts = accounts;
	}

	public MasterUsers() {}
	
//	public MasterUsers(String userName, String password, UserAccountCredential accounts) 
//	{
//		setUserName(userName);
//		setPassword(password);
//		add
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public MasterUsers add(String userName, String password) 
	{
		setUserName(userName);
		setPassword(password);
		this.accounts  = new ArrayList<UserAccount>();
		prefPass = new PreferredPassword();
//		accounts.add(new UserAccount().store("google", "myUserName", "poiuytr"));
//		accounts.add(new UserAccount().store("microsoft", "myUserName", "poiuytr"));
//		accounts.add(new UserAccount().store("yahoo", "myUserName", "poiuytr"));
//		accounts.add(new UserAccount().store("orkut", "myUserName", "poiuytr"));
		return this;
	}

	@Override
	public boolean remove(String appName) 
	{
		return false;
	}
	
	public static void showUsers()
	{
		for(MasterUsers users : masterUsers)
			System.out.println(users.toString());
	}
	
	public void showAccounts()
	{
		for(UserAccount accs : accounts)
			System.out.println(accs);
	}

	@Override
	public String toString() {
		return "MasterUsers [userName=" + userName + ", password=" + password + ", accounts=" + accounts + ", prefPass="
				+ prefPass + "]";
	}

	
	public static MasterUsers getUser(String userName) 
	{
		for(MasterUsers obj : masterUsers)
		{
			if(userName.equals(obj.getUserName()))
				return obj;
		}
		return null;
	}

	public PreferredPassword getPrefPass() {
		return prefPass;
	}

	public void setPrefPass(PreferredPassword prefPass) {
		this.prefPass = prefPass;
	}
	

}
