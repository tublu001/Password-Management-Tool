package com.epam.userAccounts;

public class UserAccount implements UserAccountCredential
{

	private String appName;
	private String url;
	private String password;
	
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "appName: " + appName + ", url: " + url + "";
	}
	

}
