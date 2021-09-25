package com.tommy.accounts.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.accounts.domain.AccountRepository;
import com.tommy.accounts.vo.AccountCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    void sut_returns_created_account() throws Exception {
        // Arrange
        AccountCreateRequest accountCreateRequest = createAccountRequest("hang19663@gmail.com");

        // Act
        ResultActions response = createAccountRequestMapping(accountCreateRequest);

        // Assert
        response.andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("email").value("hang19663@gmail.com"))
                .andExpect(jsonPath("name").value("limhangyeol"))
                .andExpect(jsonPath("accountCode").exists());
    }

    @Test
    void sut_returns_get_accounts() throws Exception {
        // Arrange
        AccountCreateRequest accountCreateRequest1 = createAccountRequest("hang19663@gmail.com");
        createAccountRequestMapping(accountCreateRequest1);

        AccountCreateRequest accountCreateRequest2 = createAccountRequest("test11@naver.com");
        createAccountRequestMapping(accountCreateRequest2);

        // Act
        ResultActions response = mockMvc.perform(get("/accounts")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        // Assert
        response.andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));
    }

    private ResultActions createAccountRequestMapping(AccountCreateRequest accountCreateRequest) throws Exception {
        return mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountCreateRequest)
                )
        ).andDo(print());
    }

    private AccountCreateRequest createAccountRequest(String email) {
        return new AccountCreateRequest(
                email,
                "limhangyeol",
                "password-origin!@"
        );
    }
}
