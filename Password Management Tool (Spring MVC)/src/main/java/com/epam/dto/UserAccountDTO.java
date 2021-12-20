package com.epam.dto;

import com.epam.model.User;
import lombok.Data;

public @Data class UserAccountDTO
{
    private User user;
    private String appName;
    private String url;
    private String password;
    private String groupName;

    public UserAccountDTO(User user, String appName, String url, String password, String groupName)
    {
        this.user = user;
        this.appName = appName;
        this.url = url;
        this.password = password;
        this.groupName = groupName;
    }
}
