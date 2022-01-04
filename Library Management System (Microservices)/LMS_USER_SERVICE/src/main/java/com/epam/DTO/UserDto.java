package com.epam.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.epam.utils.Constants.*;

public @Data class UserDto
{
    @NotBlank(message = USER_USERNAME_MANDATORY)
    private String userName;

    @NotBlank(message = USER_EMAIL_MANDATORY)
    @Email(message = INVALID_EMAIL)
    private String userEmail;

    @NotBlank(message = USER_NAME_MANDATORY)
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
