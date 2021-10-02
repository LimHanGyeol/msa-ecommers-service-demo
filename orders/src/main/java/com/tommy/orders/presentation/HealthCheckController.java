package com.tommy.orders.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HealthCheckController {

    private final Environment environment;

    @GetMapping("/health_check")
    public String healthCheck() {
        String port = environment.getProperty("local.server.port");
        return String.format("It's Working in Orders Service on PORT %s", port);
    }
}
