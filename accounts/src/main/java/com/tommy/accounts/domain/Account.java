package com.tommy.accounts.domain;

import com.tommy.accounts.dto.AccountDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "ec_account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String accountCode;

    @Column(nullable = false)
    private String encryptedPassword;

    public Account(String email, String name, String accountCode, String encryptedPassword) {
        this.email = email;
        this.name = name;
        this.accountCode = accountCode;
        this.encryptedPassword = encryptedPassword;
    }

    public Account(AccountDto accountDto) {
        this.email = accountDto.getEmail();
        this.name = accountDto.getName();
        this.accountCode = accountDto.getAccountCode();
        this.encryptedPassword = accountDto.getEncryptedPassword();
    }

    public void updateEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
