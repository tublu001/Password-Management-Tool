package com.epam.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class UserDto
{
    private String userName;
    private String userEmail;
    private String name;

    public UserDto(String userName, String userEmail, String name)
    {
        this.userName = userName;
        this.userEmail = userEmail;
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
