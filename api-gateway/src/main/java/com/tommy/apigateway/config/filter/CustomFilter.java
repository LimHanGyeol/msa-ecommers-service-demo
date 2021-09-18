package com.tommy.apigateway.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(CustomFilter.Config config) {
        // return getGatewayFilterCode();
        // Custom Pre Filter.Suppose we can extract JWT and perform Authentication
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE Filter: request uri -> {}", request.getId());
            // Custom POST Filter.Suppose we can call error response handler based on error code.
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        log.info("Custom POST Filter: response code -> {}", response.getStatusCode());
                    }));
        };
    }

    private GatewayFilter getGatewayFilterCode() {
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE filter: request id {}", request.getId());

            // post filter 동작
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> log.info("Custom POST filter: response code -> {}", response.getStatusCode())
                            )
                    );
        }, Ordered.HIGHEST_PRECEDENCE);
        return filter;
    }

    public static class Config {
        // Put the configuration properties
    }
}
