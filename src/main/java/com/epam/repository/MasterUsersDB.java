package com.epam.repository;

import java.util.ArrayList;

import com.epam.model.MasterUser;

public class MasterUsersDB 
{
	private static ArrayList<MasterUser> masterUsers = new ArrayList<MasterUser>();
	
	
	public static ArrayList<MasterUser> getMasterUsers() {
		return masterUsers;
	}

	public static void setMasterUsers(ArrayList<MasterUser> masterUsers) {
		MasterUsersDB.masterUsers = masterUsers;
	}
	

}

