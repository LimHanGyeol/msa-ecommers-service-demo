package com.tommy.accounts.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountCode(String accountCode);
    Optional<Account> findByEmail(String username);
}
