package com.reactive.reactiveclient;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class PersonRequestor {

    @Bean
    RSocketRequester rSocketRequester(RSocketRequester.Builder rsocketBuilder) {
        return rsocketBuilder.connectTcp("localhost",7001).block();
    }
}
