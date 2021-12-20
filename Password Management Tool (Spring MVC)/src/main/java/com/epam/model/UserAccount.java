package com.epam.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "User_Accounts")
public @Data class UserAccount
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

}
