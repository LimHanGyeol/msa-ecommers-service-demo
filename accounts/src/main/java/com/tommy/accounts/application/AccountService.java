package com.tommy.accounts.application;

import com.tommy.accounts.domain.Account;
import com.tommy.accounts.dto.AccountDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountByAccountCode(String accountCode);
    List<Account> findAllAccount();
}
