/**
 * 
 */
package com.epam.model;

import java.util.ArrayList;

import com.epam.passwordOperations.PreferredPassword;

/**
 * @author Manash_Rauta
 *
 */
public class User
{
	private String userName;
	private String password;
	private ArrayList<UserAccount> accounts;
	private ArrayList<String> groups;
	private PreferredPassword prefPass;	
	

	public void setAccounts(ArrayList<UserAccount> accounts) {
		this.accounts = accounts;
	}

	public User() {}

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

	public PreferredPassword getPrefPass() {
		return prefPass;
	}

	public void setPrefPass(PreferredPassword prefPass) {
		this.prefPass = prefPass;
	}

	public ArrayList<String> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<String> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "MasterUsers [userName=" + userName + ", password=" + password + ", accounts=" + accounts + ", prefPass="
				+ prefPass + "]";
	}

	public ArrayList<UserAccount> getAccounts() {
		return accounts;
	}
	

}
