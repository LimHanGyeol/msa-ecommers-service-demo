package com.tommy.accounts.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.accounts.vo.AccountCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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

    @Test
    void sut_returns_created_account() throws Exception {
        // Arrange
        AccountCreateRequest accountCreateRequest = createAccountRequest();

        // Act
        ResultActions response = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountCreateRequest)
                )
        ).andDo(print());

        // Assert
        response.andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("email").value("hang19663@gmail.com"))
                .andExpect(jsonPath("name").value("limhangyeol"))
                .andExpect(jsonPath("userId").exists());
    }

    private AccountCreateRequest createAccountRequest() {
        return new AccountCreateRequest(
                "hang19663@gmail.com",
                "limhangyeol",
                "password-origin!@"
        );
    }
}
