package com.epam.dto;

import com.epam.model.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public @Data class UserAccountDTO
{
    private User user;

    private String appName;
    private String url;
    private String password;
    private String groupName;
    private String masterName;
    private String masterPassword;

    public UserAccountDTO(User user, String appName, String url, String password, String groupName)
    {
        this.user = user;
        this.appName = appName;
        this.url = url;
        this.password = password;
        this.groupName = groupName;
    }

    @Override
    public String toString()
    {
        return "UserAccountDTO{" +
                "appName='" + appName + '\'' +
                ", url='" + url + '\'' +
                ", password='" + password + '\'' +
                ", groupName='" + groupName + '\'' +
                ", masterName='" + masterName + '\'' +
                '}';
    }
}
