package com.epam.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public @Data class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false)
    private Long id;
    private String userName;
    private String userEmail;
    private String name;

    public User(String userName, String userEmail, String name)
    {
        this.userName = userName;
        this.userEmail = userEmail;
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
