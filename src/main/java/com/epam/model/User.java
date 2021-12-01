/**
 * 
 */
package com.epam.model;

import java.util.ArrayList;

import com.epam.passwordOperations.PreferredPassword;

import javax.persistence.*;

/**
 * @author Manash_Rauta
 *
 */
@Entity
@Table(name="Master_Users")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private String userName;
	private String password;
	private ArrayList<UserAccount> accounts;
	private ArrayList<String> groups;
	private PreferredPassword prefPass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


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
