package com.epam.repository;

import java.util.ArrayList;

import com.epam.model.User;

public class MasterUsersDB 
{
	private static ArrayList<User> users = new ArrayList<User>();
	
	
	public static ArrayList<User> getMasterUsers() {
		return users;
	}

	public static void setMasterUsers(ArrayList<User> users) {
		MasterUsersDB.users = users;
	}
	

}

