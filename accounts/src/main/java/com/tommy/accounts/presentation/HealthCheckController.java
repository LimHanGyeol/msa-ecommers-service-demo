package com.tommy.accounts.presentation;

import com.tommy.accounts.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class HealthCheckController {

    private final Environment environment;
    private final Greeting greeting;

    @GetMapping("/accounts-service/health_check")
    public String healthCheck() {
        String port = environment.getProperty("local.server.port");
        return String.format("It's Working in Accounts Service on PORT : %s", port);
    }

    @GetMapping("/accounts-service/welcome")
    public String welcome() {
        return greeting.getMessage();
    }
}
