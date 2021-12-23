package com.epam.repository;

import com.epam.security.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>
{

    public AuthUser findByUsername(String username);
}
