package com.tommy.accounts.presentation;

import com.tommy.accounts.application.AccountService;
import com.tommy.accounts.domain.Account;
import com.tommy.accounts.dto.AccountDto;
import com.tommy.accounts.vo.AccountCreateRequest;
import com.tommy.accounts.vo.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountCreateRequest accountCreateRequest) {
        AccountDto accountDto = new AccountDto(accountCreateRequest);
        accountService.createAccount(accountDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountDto.getUserId())
                .toUri();

        AccountResponse accountResponse = new AccountResponse(accountDto);
        return ResponseEntity.created(uri).body(accountResponse);
    }

    @GetMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountResponse>> getAccounts() {
        List<Account> accounts = accountService.findAllAccount();

        List<AccountResponse> accountResponses = accounts.stream()
                .map(AccountResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(accountResponses);
    }
}
