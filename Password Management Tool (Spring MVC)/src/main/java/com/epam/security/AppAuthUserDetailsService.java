package com.epam.security;

import com.epam.repository.AuthGroupRepository;
import com.epam.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppAuthUserDetailsService implements UserDetailsService
{
    @Autowired
    private final AuthUserRepository authUserRepository;
    @Autowired
    private final AuthGroupRepository authGroupRepository;

    public AppAuthUserDetailsService(AuthUserRepository userRepository, AuthGroupRepository authGroupRepository)
    {
        super();
        this.authUserRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        AuthUser authUser = authUserRepository.findByUsername(username);
        if (authUser == null)
        {
            throw new UsernameNotFoundException("Cannot find username : " + username);
        }

        List<AuthGroup> authGroups = authGroupRepository.findByUsername(username);

        return new AuthUserPrincipal(authUser, authGroups);
    }

}
