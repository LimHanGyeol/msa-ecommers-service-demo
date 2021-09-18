package com.tommy.apigateway.config.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // filter에서 하고 싶은 내용을 재정의
        // pre filter 동작
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global filter message: {}", config.getMessage());

            if (config.isPostLogger()) {
                log.info("Global filter Start: request id -> {}", request.getId());
            }
            // post filter 동작
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> { // Spring 5에서 지원하는 기능으로 비동기로 값을 전달할 때 사용되는 객체
                        if (config.isPostLogger()) {
                            log.info("Global filter End: response code -> {}", response.getStatusCode());
                        }
                    }));
        });
    }

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Config {
        // configuration 정보 입력
        private String message;
        private boolean preLogger;
        private boolean postLogger;
    }
}
