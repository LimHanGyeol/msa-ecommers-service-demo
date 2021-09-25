package com.tommy.accounts.application;

import com.tommy.accounts.domain.Account;
import com.tommy.accounts.domain.AccountRepository;
import com.tommy.accounts.dto.AccountDto;
import com.tommy.accounts.vo.AccountOrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        String userId = UUID.randomUUID().toString();
        accountDto.setAccountCode(userId);

        Account account = new Account(accountDto);
        account.updateEncryptedPassword(passwordEncoder.encode(accountDto.getPassword()));
        Account savedAccount = accountRepository.save(account);
        return new AccountDto(savedAccount, Collections.emptyList());
    }

    @Override
    public AccountDto getAccountByAccountCode(String accountCode) {
        Account account = accountRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found!"));

        List<AccountOrdersResponse> accountOrdersResponses = Collections.emptyList();

        return new AccountDto(account, accountOrdersResponses);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }
}
