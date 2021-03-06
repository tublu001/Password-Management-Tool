package com.epam.repository;

import com.epam.security.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long>
{

    AuthUser findByUsername(String username);
}
