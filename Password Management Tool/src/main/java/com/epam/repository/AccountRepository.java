package com.epam.repository;

import com.epam.model.UserAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<UserAccount, Long>
{
    public Optional<UserAccount> findById(Long id);
    @Transactional
    @Modifying
    @Query("DELETE FROM UserAccount WHERE id=:id")
    public void deleteAllAccounts(Long id);
}
