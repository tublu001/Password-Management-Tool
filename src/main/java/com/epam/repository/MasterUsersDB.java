package com.epam.repository;

import java.util.ArrayList;

import com.epam.model.MasterUsers;

public class MasterUsersDB 
{
	private static ArrayList<MasterUsers> masterUsers = new ArrayList<MasterUsers>();
	
	
	public static ArrayList<MasterUsers> getMasterUsers() {
		return masterUsers;
	}

	public static void setMasterUsers(ArrayList<MasterUsers> masterUsers) {
		MasterUsersDB.masterUsers = masterUsers;
	}
	

}

