package com.epam.security;

import com.epam.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppAuthUserDetailsService implements UserDetailsService
{

    private final AuthUserRepository repository;

    public AppAuthUserDetailsService(AuthUserRepository repository)
    {
        super();
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        AuthUser user = repository.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Cannot find username : " + username);
        }
        return new AuthUserPrincipal(user);
    }

}
