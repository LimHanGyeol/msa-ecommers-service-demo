package com.tommy.accounts.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.accounts.application.AccountService;
import com.tommy.accounts.dto.AccountDto;
import com.tommy.accounts.vo.AccountLoginRequest;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AccountService accountService;
    private final Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager, AccountService accountService, Environment environment) {
        super(authenticationManager);
        this.accountService = accountService;
        this.environment = environment;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            AccountLoginRequest credential = new ObjectMapper().readValue(request.getInputStream(), AccountLoginRequest.class);
            UsernamePasswordAuthenticationToken token = createUsernamePasswordAuthenticationToken(credential);
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private UsernamePasswordAuthenticationToken createUsernamePasswordAuthenticationToken(AccountLoginRequest credential) {
        return new UsernamePasswordAuthenticationToken(credential.getEmail(), credential.getPassword(), List.of());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        AccountDto userDetails = accountService.getUserDetailsByEmail(username);
    }
}
