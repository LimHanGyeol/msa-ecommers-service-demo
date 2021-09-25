package com.tommy.accounts.application;

import com.tommy.accounts.domain.Account;
import com.tommy.accounts.domain.AccountRepository;
import com.tommy.accounts.dto.AccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AccountServiceImplTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl(accountRepository, passwordEncoder);
        accountRepository.deleteAll();
    }

    @Test
    void sut_returns_correct() {
        // Arrange
        AccountDto accountDto = createAccountDto("hang19663@gmail.com");

        // Act
        AccountService sut = accountService;
        AccountDto createdAccount = sut.createAccount(accountDto);

        // Assert
        assertThat(accountDto).isNotEqualTo(createdAccount);
        assertThat(createdAccount.getAccountCode()).isNotBlank();
        assertThat(createdAccount.getEncryptedPassword()).isNotEqualTo(accountDto.getPassword());
    }

    @Test
    void sut_returns_find_all_account() {
        // Arrange
        AccountDto accountDto1 = createAccountDto("hang19663@gmail.com");
        accountService.createAccount(accountDto1);

        AccountDto accountDto2 = createAccountDto("test11@gmail.com");
        accountService.createAccount(accountDto2);

        // Act
        AccountService sut = accountService;
        List<Account> accounts = sut.findAllAccount();

        // Assert
        assertThat(accounts).hasSize(2);
        List<String> accountsEmails = accounts.stream()
                .map(Account::getEmail)
                .collect(Collectors.toList());
        assertThat(accountsEmails).containsExactly("hang19663@gmail.com", "test11@gmail.com");
    }

    @Test
    void sut_returns_get_account_by_account_code() {
        // Arrange
        AccountDto accountDto1 = createAccountDto("hang19663@gmail.com");
        accountService.createAccount(accountDto1);

        List<Account> accounts = accountRepository.findAll();
        Account account = accounts.get(0);

        // Act
        AccountService sut = accountService;
        AccountDto accountDto = sut.getAccountByAccountCode(account.getAccountCode());

        // Assert
        assertThat(accountDto.getAccountCode()).isEqualTo(account.getAccountCode());
        assertThat(accountDto.getEmail()).isEqualTo("hang19663@gmail.com");
    }

    private AccountDto createAccountDto(String email) {
        return new AccountDto(
                email,
                "limhangyeol",
                "password123!"
        );
    }
}
