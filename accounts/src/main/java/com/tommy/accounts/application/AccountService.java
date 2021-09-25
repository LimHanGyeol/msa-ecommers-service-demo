package com.tommy.accounts.application;

import com.tommy.accounts.domain.Account;
import com.tommy.accounts.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    List<Account> findAllAccount();
}
