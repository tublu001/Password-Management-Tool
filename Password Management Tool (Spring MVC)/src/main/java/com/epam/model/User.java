/**
 *
 */
package com.epam.model;

import com.epam.service.password_operations.PreferredPassword;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manash_Rauta
 *
 */
@Entity
@Table(name = "Master_Users")
public @Data
class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long userId = 0L;

    private String userName;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserAccount> accounts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "groupName")
    private List<String> groups = new ArrayList<>();

    @Embedded
    private PreferredPassword preferredPassword = new PreferredPassword();

    public User()
    {
    }

    public User(String userName, String password, List<UserAccount> accounts, List<String> groups, PreferredPassword preferredPassword)
    {
        this.userName = userName;
        this.password = password;
        this.accounts = accounts;
        this.groups = groups;
        this.preferredPassword = preferredPassword;
    }

    @JsonManagedReference
    public List<UserAccount> getAccounts()
    {
        return accounts;
    }

}
