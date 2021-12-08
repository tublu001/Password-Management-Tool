package com.epam.model;

import com.epam.model.User;

public class UserData
{
    private User user;
    private String appName;
    private String url;
    private String password;
    private String groupName;

    public UserData(User user, String appName, String url, String password, String groupName) {
        this.user = user;
        this.appName = appName;
        this.url = url;
        this.password = password;
        this.groupName = groupName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
