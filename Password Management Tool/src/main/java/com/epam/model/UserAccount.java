package com.epam.model;

import javax.persistence.*;

@Entity
@Table(name = "User_Accounts")
public class UserAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_id")
    private final Long id = 0l;

    private String appName;
    private String url;
    private String password;
    private String accountGroup = "Undefined";

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public UserAccount()
    {
    }

    public UserAccount(String appName, String url, String password, String group, User user)
    {
        this.appName = appName;
        this.url = url;
        this.password = password;
        this.accountGroup = group;
        this.user = user;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAccountGroup()
    {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup)
    {
        this.accountGroup = accountGroup;
    }

    @Override
    public String toString()
    {
        return "appName: " + appName + ", url: " + url;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
