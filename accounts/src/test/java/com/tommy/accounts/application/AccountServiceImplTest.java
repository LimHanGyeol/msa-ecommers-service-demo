package com.tommy.accounts.application;

import com.tommy.accounts.domain.AccountRepository;
import com.tommy.accounts.dto.AccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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
        AccountDto accountDto = new AccountDto(
                "hang19663@gmail.com",
                "limhangyeol",
                "password123!"
        );

        // Act
        AccountService sut = accountService;
        AccountDto createdAccount = sut.createAccount(accountDto);

        // Assert
        assertThat(accountDto).isNotEqualTo(createdAccount);
        assertThat(createdAccount.getUserId()).isNotBlank();
        assertThat(createdAccount.getEncryptedPassword()).isNotEqualTo(accountDto.getPassword());
    }
}
