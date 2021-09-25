package com.tommy.accounts.dto;

import com.tommy.accounts.domain.Account;
import com.tommy.accounts.vo.AccountCreateRequest;
import com.tommy.accounts.vo.AccountOrdersResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
public class AccountDto {
    private final String email;
    private final String name;
    private String password;
    private String userId;
    private LocalDateTime createdAt;
    private String encryptedPassword;

    private List<AccountOrdersResponse> accountOrdersResponses;

    public AccountDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.createdAt = LocalDateTime.now();;
    }

    public AccountDto(Account account, List<AccountOrdersResponse> accountOrdersResponses) {
        this.email = account.getEmail();
        this.name = account.getName();
        this.userId = account.getAccountCode();
        this.encryptedPassword = account.getEncryptedPassword();
        this.accountOrdersResponses = accountOrdersResponses;
    }

    public AccountDto(AccountCreateRequest accountCreateRequest) {
        this.email = accountCreateRequest.getEmail();
        this.name = accountCreateRequest.getName();
        this.password = accountCreateRequest.getPassword();
        this.createdAt = LocalDateTime.now();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(userId, that.userId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(encryptedPassword, that.encryptedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password, userId, createdAt, encryptedPassword);
    }
}
