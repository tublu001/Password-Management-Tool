package com.epam.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AuthUserPrincipal implements UserDetails
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private AuthUser authUser;
    private List<AuthGroup> authGroups;

    public AuthUserPrincipal(AuthUser authUser, List<AuthGroup> authGroups)
    {
        super();
        this.authUser = authUser;
        this.authGroups = authGroups;
    }

    public AuthUser getUser()
    {
        return authUser;
    }

    public void setUser(AuthUser authUser)
    {
        this.authUser = authUser;
    }

    public List<AuthGroup> getAuthGroups()
    {
        return authGroups;
    }

    public void setAuthGroups(List<AuthGroup> authGroups)
    {
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {

        Optional<List<AuthGroup>> optionalAuthGroups = Optional.of(authGroups);

        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        if (optionalAuthGroups.isPresent())
        {
            authGroups.forEach(group ->
            {
                grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
            });
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword()
    {
        return this.authUser.getPassword();
    }

    @Override
    public String getUsername()
    {
        return this.authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {

        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {

        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

}
