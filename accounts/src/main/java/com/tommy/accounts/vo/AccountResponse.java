package com.tommy.accounts.vo;

import com.tommy.accounts.dto.AccountDto;
import lombok.Getter;

import java.util.Objects;

@Getter
public class AccountResponse {
    private final String email;
    private final String name;
    private final String userId;

    public AccountResponse(String email, String name, String userId) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

    public AccountResponse(AccountDto accountDto) {
        this.email = accountDto.getEmail();
        this.name = accountDto.getName();
        this.userId = accountDto.getUserId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountResponse that = (AccountResponse) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, userId);
    }
}
