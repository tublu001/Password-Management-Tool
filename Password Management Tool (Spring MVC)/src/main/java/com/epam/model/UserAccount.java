package com.epam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "User_Accounts")
public @Data
class UserAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_id")
    private final Long id = 0l;

    private String appName;
    private String url;
    private String password;
    private String groupName = "Undefined";

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
        this.groupName = group;
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "UserAccount{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", url='" + url + '\'' +
                ", password='" + password + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @JsonBackReference
    public User getUser()
    {
        return user;
    }
}
