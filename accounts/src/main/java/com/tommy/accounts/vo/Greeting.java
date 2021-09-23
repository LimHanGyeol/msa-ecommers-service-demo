package com.tommy.accounts.vo;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * 생성자 바인딩을 이용하기 때문에 불변 객체로 만든다.
 */
@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "greeting")
public class Greeting {
    private final String message;
}
