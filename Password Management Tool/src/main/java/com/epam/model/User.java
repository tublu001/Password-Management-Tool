/**
 *
 */
package com.epam.model;

import com.epam.passwordOperations.PreferredPassword;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manash_Rauta
 *
 */
@Entity
@Table(name = "Master_Users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long user_Id = 0L;

    private String userName;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserAccount> accounts = new ArrayList<>();

    @ElementCollection
    @Column(name = "groupName")
    private List<String> groups = new ArrayList<>();

    @Embedded
    private PreferredPassword prefPass = new PreferredPassword();

    public Long getUser_Id()
    {
        return user_Id;
    }

    public void setUser_Id(Long user_Id)
    {
        this.user_Id = user_Id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<String> getGroups()
    {
        return groups;
    }

    public void setGroups(List<String> groups)
    {
        this.groups = groups;
    }

    public PreferredPassword getPrefPass()
    {
        return prefPass;
    }

    public void setPrefPass(PreferredPassword prefPass)
    {
        this.prefPass = prefPass;
    }

    public void setAccounts(List<UserAccount> accounts)
    {
        this.accounts = accounts;
    }

    public void setAccount(UserAccount account)
    {
        this.accounts.add(account);
    }

    @Override
    public String toString()
    {
        return "MasterUsers [userName=" + userName + ", password=" + password + "]";
    }

    public List<UserAccount> getAccounts()
    {
        return accounts;
    }

    public User(String userName, String password, List<UserAccount> accounts, List<String> groups, PreferredPassword prefPass)
    {
        this.userName = userName;
        this.password = password;
        this.accounts = accounts;
        this.groups = groups;
        this.prefPass = prefPass;
    }

    public User()
    {
    }

}
