package com.epam.MasterGroups;

public interface MasterUserCredentials 
{
	public MasterUsers add(String userName, String password);
	public boolean remove(String appName);
}
