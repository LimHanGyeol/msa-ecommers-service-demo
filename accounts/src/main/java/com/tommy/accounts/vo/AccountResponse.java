package com.tommy.accounts.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tommy.accounts.domain.Account;
import com.tommy.accounts.dto.AccountDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    private final String email;
    private final String name;
    private final String accountCode;
    private final List<AccountOrdersResponse> accountOrdersResponses;

    public AccountResponse(Account account) {
        this.email = account.getEmail();
        this.name = account.getName();
        this.accountCode = account.getAccountCode();
        this.accountOrdersResponses = new ArrayList<>();
    }

    public AccountResponse(AccountDto accountDto) {
        this.email = accountDto.getEmail();
        this.name = accountDto.getName();
        this.accountCode = accountDto.getAccountCode();
        this.accountOrdersResponses = accountDto.getAccountOrdersResponses();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountResponse that = (AccountResponse) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(accountCode, that.accountCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, accountCode);
    }
}
